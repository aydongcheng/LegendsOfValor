import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//the entity of game monster and hero
public class ValorGame extends RPGGame{
    private LVBoard lvBoard;
    //private Team team;
    private Scanner scan;

    private int roundCounter=0;
	private MonsterTeam mTeam;
	private HeroTeam hTeam;

    public ValorGame(){
       scan = new Scanner(System.in);
       hTeam = new HeroTeam();
       mTeam = new MonsterTeam();
    }


    public void start(){
        // Play BGM
        Utils.playSound("BabyElephantWalk300.wav");

        // ----- //
        // Welcome
        // ----- //
    	Window welcomeWindow = new WelcomeWindow();
        Window.newMessage("----------------------------");
    	Window.newMessage("Welcome to Legends of Valor!");
        Window.newMessage("----------------------------");
        Window.newMessage("Press Enter to start: ");
        System.out.print(welcomeWindow);
        // Press Enter to start
        scan.nextLine();

        // ----- //
    	//pick hero phase, choose 3
        // ----- //
        chooseHero();
        //TODO A method to print info of two teams & put messages into log
        Window.newMessage("__clear__");
        Window.newMessage("You have built a Team of:");
        Window.newMessage(hTeam.displayHerosNameString());
        Window.newMessage(mTeam.displayMonstersNameString());

        // ----- //
        // Start
        // ----- //
        Window combatWindow = new CombatWindow();
        lvBoard = ((CombatWindow)combatWindow).getTheBoard();

        //initialize heroes' locations
        for(int i=0,j=0; i<=8; i+=3,j++) {
            Hero heroTmp = hTeam.getHeroes().get(j);
            lvBoard.cells[7][i].arrive(heroTmp, j, true);
            heroTmp.move(7, i);
        }

        // initialize monsters' locations
        for(int i=0,j=0; i<=8; i+=3,j++) {
            Monster monsterTmp = mTeam.getMonsters().get(j);
            lvBoard.cells[0][i].arrive(monsterTmp, monsterTmp.getIdx(), false);
            monsterTmp.move(0, i);
        }

        while (true) {
        	roundCounter++;
        	//create new monsters every 8 round
        	if(roundCounter!=0 && roundCounter%8==0){
        	    for(int i=0, j=0; i<=8; i+=3,j++){
        	        Monster newMonster = RandomMonsterCreator.createMonster(hTeam.getHeroes().get(j).getLevel());
                    lvBoard.cells[0][i].arrive(newMonster, newMonster.getIdx(), false);
                    newMonster.move(0, i);
                    mTeam.getMonsters().add(newMonster);
                }
            }

        	Window.newMessage("This is round" + roundCounter + ", Hero Action Phase:");
            for(Hero h : hTeam.getHeroes()) {
                Window.newMessage("This is " + h.getName() + "'s turn");
                //call the method to choose actions for each of the heroes
	            chooseAction(h, (CombatWindow)combatWindow);
            }

            Window.newMessage("Hero Phase end. Monster Action Phase:");
            Window.show();

            for(Monster m : mTeam.getMonsters()) {
                // if no target in range, then move forward:
                ArrayList<Characters> targetList = getTargetList(m.getRow(), m.getColumn(), "hero");
            	if (targetList.size() != 0) {
            		// select target
                    int randomID = (int)(Math.random() * targetList.size());
                    Hero targetHero = (Hero)targetList.get(randomID);

                    // attack
                    int monsterDamage = m.attack();
                    int heroGetHurt = targetHero.getHurt(monsterDamage);
                    Window.newMessage("Monster "+ m.getName() +" cast "+ heroGetHurt + " damage to hero "+ targetHero.getName());

                    if(targetHero.isFaint()){
                        lvBoard.cells[targetHero.getRow()][targetHero.getColumn()]
                                .leave(targetHero, hTeam.getHeroes().indexOf(targetHero), true);
                        targetHero.move(7, targetHero.getColumn());
                        lvBoard.cells[7][targetHero.getColumn()]
                                .arrive(targetHero, hTeam.getHeroes().indexOf(targetHero), true);
                    }
                    Window.show();
            	}
            	else {
                    //move, when no target in range
                    int rowCurrent = m.getRow();
                    int colCurrent = m.getColumn();
                    int rowNext = rowCurrent + 1;
                    int colNext = colCurrent;
                    if (checkMove(rowNext, colNext, "monster")) {
                    	int id = m.getIdx();
                        lvBoard.cells[rowCurrent][colCurrent].leave(m, id, false);
                        m.move(rowNext, colNext);
                        lvBoard.cells[rowNext][colNext].arrive(m, id, false);
                        if(rowNext == 7){
                            Window.newMessage("Monsters Win!!!");
                            quitGame();
                        }
                    }
                    //else, try the other column
                    else if(checkMove(rowCurrent, colNext+1, "monster")) {
                    	int id = m.getIdx();
                        lvBoard.cells[rowCurrent][colCurrent].leave(m, id, false);
                        m.move(rowCurrent, colNext+1);
                        lvBoard.cells[rowCurrent][colNext+1].arrive(m, id, false);
                    }
                    else if (checkMove(rowCurrent, colNext-1, "monster")) {
                    	int id = m.getIdx();
                        lvBoard.cells[rowCurrent][colCurrent].leave(m, id, false);
                        m.move(rowCurrent, colNext-1);
                        lvBoard.cells[rowCurrent][colNext-1].arrive(m, id, false);
                    }

            	}
            }
            for(Hero h : hTeam.getHeroes()){
                if(!h.isFaint()){
                    h.recover();
                }
                else {
	        		h.revive();
	                h.setFaint(false);
                }
            }
        	Window.newMessage("End of round " + roundCounter + "!\n^*^*^*^*^*^*^*^*^*^*^*^*^*^\n");
            Window.newMessage(Displayer.listDisplay(hTeam.getHeroes(),"Heroes",0));
            Window.show();
        }

    }

    // find target of type targetType in a 3x3 grid around row and col.
    private ArrayList<Characters> getTargetList(int row, int col, String targetType) {
        ArrayList<Characters> targetList = new ArrayList<Characters>();
        for (int idx_1 = 0; idx_1 < 3; idx_1++) {
            for (int idx_2 = 0; idx_2 < 3; idx_2++) {
                int rowTmp = row - 1 + idx_1;
                int colTmp = col - 1 + idx_2;

                boolean cond1 = (rowTmp >=0) && (rowTmp <= 7);
                boolean cond2 = (colTmp >=0) && (colTmp <= 7);
                if (!(cond1 && cond2)) {
                    continue;
                }

                Cell cellTmp = lvBoard.cells[rowTmp][colTmp];

                if (targetType.equals("hero")) {
                    if (!cellTmp.hasHero()) {
                        continue;
                    }
                    targetList.add(cellTmp.getHero());
                }

                if (targetType.equals("monster")) {
                    if (!cellTmp.hasMonster()) {
                        continue;
                    }
                    targetList.add(cellTmp.getMonster());
                }
            }
        }
        return targetList;
    }


    //choose the action for each of heroes
    private void chooseAction(Hero h, CombatWindow c){
        int id = hTeam.getHeroes().indexOf(h);
        int rowCurrent = h.getRow();
        int colCurrent = h.getColumn();

        outerLoop:
        while (true) {
            System.out.print(c);
            int input = Utils.safeIntInput("Please choose an action according to the manual on your right: ", 0, 9);
            switch(input) {
                case 1:
                    Window.newMessage("Please choose your direction:");
                    char[] allowedChars = {'w', 'a', 's', 'd'};
                    char direction = Utils.safeCharInput("Input: ", allowedChars);

                    int rowNext = 0;
                    int colNext = 0;
                    if (direction == 'w'){
                    	//check for targets in range
                        ArrayList<Characters> targetListAttack = getTargetList(rowCurrent, colCurrent, "monster");
                        if (targetListAttack.size() != 0) {
                            Window.newMessage("A monster is in your way!");
                            break;
                        }
                        rowNext = h.getRow() - 1;
                        colNext = h.getColumn();
                    }
                    if (direction == 'a'){
                        rowNext = h.getRow();
                        colNext = h.getColumn() - 1;
                    }
                    if (direction == 's'){
                        rowNext = h.getRow() + 1;
                        colNext = h.getColumn();
                    }
                    if (direction == 'd'){
                        rowNext = h.getRow();
                        colNext = h.getColumn() + 1;
                    }
                    //check whether the next cell is accessible, or occupied
                    if (checkMove(rowNext, colNext, "hero")) {
                        //arrive, leave
                        lvBoard.cells[rowCurrent][colCurrent].leave(h, id, true);
                        h.move(rowNext, colNext);
                        lvBoard.cells[rowNext][colNext].arrive(h, id, true);
                        //update highest position record
                        hTeam.updateHighest();
                        if(rowNext == 0) {
                            Window.newMessage("Heroes Win!!!");
                            quitGame();
                        }
                        break outerLoop;
                    }
                    Window.newMessage("You cannot go there!");
                    break;

                //check info
                case 2:
                    checkInfo(h);
                    break;

                //buy
                case 3:
                    //new market
                    if(lvBoard.getCells()[h.getRow()][h.getColumn()] instanceof NexusCell) {
                        //trade
                        trade(h);
                    }
                    else{
                        Window.newMessage("You are not in nexus, you cannot buy anything!");
                    }
                    break;

                //Attack
                case 4:
                    ArrayList<Characters> targetListAttack = getTargetList(rowCurrent, colCurrent, "monster");
                    if (targetListAttack.size() == 0) {
                        Window.newMessage("No visible target!");
                        break;
                    }

                    Monster targetTmpAttack = chooseMonster(targetListAttack);

                    // perform attack
                    int actualDamage = targetTmpAttack.getHurt(h.attack());
                    Window.newMessage(h.getName()+" dealt "+actualDamage+" damages to "+targetTmpAttack.getName());

                    if(targetTmpAttack.isFaint()){
                        lvBoard.cells[targetTmpAttack.getRow()][targetTmpAttack.getColumn()]
                                .leave(targetTmpAttack, targetTmpAttack.getIdx(), false);
                        mTeam.getMonsters().remove(targetTmpAttack);
                        h.getRewards(targetTmpAttack.getLevel());
                    }

                    break outerLoop;

                //Cast spell
                case 5:
                    ArrayList<Characters> targetListSpell = getTargetList(rowCurrent, colCurrent, "monster");
                    if (targetListSpell.size() == 0) {
                        Window.newMessage("No visible target!");
                        break;
                    }

                    Monster targetTmpSpell = chooseMonster(targetListSpell);

                    // perform spell
                    int heroDamage = h.SpellAttack();
                    if(heroDamage==0){
                        Window.newMessage("Hero can't cast a spell!");
                        break;
                    }
                    int monsterGethurt = targetTmpSpell.getHurt(heroDamage);
                    Window.newMessage("Hero "+ h.getName() +" dealt "+ monsterGethurt + " damage to monster "+ targetTmpSpell.getName());
                    h.getSpell().specialEffect(targetTmpSpell);
                    Window.newMessage("Monster "+ targetTmpSpell.getName() + " " + h.getSpell().getSpecil());

                    if(targetTmpSpell.isFaint()){
                        lvBoard.cells[targetTmpSpell.getRow()][targetTmpSpell.getColumn()]
                                .leave(targetTmpSpell, targetTmpSpell.getIdx(), false);
                        mTeam.getMonsters().remove(targetTmpSpell);
                        h.getRewards(targetTmpSpell.getLevel());
                    }
                    Window.show();
                    break outerLoop;

                //Teleport
                case 6:
                    Window.newMessage("You are teleporting to:");
                    Window.newMessage("Row (1~8):");
                    int rowTeleport = Utils.safeIntInput("Input: ", 1, 8) - 1;
                    Window.newMessage("Column (1~8):");
                    int colTeleport = Utils.safeIntInput("Input: ", 1, 8) - 1;

                    //TODO check monster

                    // check Move
                    if(!checkMove(rowTeleport, colTeleport, "hero")) {
                        Window.newMessage("You cannot go there!");
                        break;
                    }

                    // check highest
                    if (rowTeleport < hTeam.getHighest()) {
                        Window.newMessage("This hero can't teleport to a location higher than previous highest record");
                        break;
                    }

                    // Teleport
                    lvBoard.cells[rowCurrent][colCurrent].leave(h, id, true);
                    h.move(rowTeleport, colTeleport);
                    lvBoard.cells[rowTeleport][colTeleport].arrive(h, id, true);
                    break outerLoop;

                //Back to Nexus
                case 7:
                    int rowNexus = 7;
                    int colNexus = h.getColumn();

                    // check Move
                    if(!checkMove(rowNexus, colNexus, "hero")) {
                        Window.newMessage("You cannot go there!");
                        break;
                    }

                    // Teleport to Nexus
                    lvBoard.cells[rowCurrent][colCurrent].leave(h, id, true);
                    h.move(rowNexus, colNexus);
                    lvBoard.cells[rowNexus][colNexus].arrive(h, id, true);
                    break outerLoop;

                //End turn
                case 8:
                    break outerLoop;

                //Quit
                case 9:
                    quitGame();
                    break;
            }

        }

    	Window.newMessage(h.getName() + "'s turn ends.\n^*^*^*^*^*^*^*^*^*^*^*^*^*^\n");
    }

    // check whether the new cell is accessible
    // (inaccessible cell, or a cell that has a character of the same type)
    private boolean checkMove(int row, int column, String subjectType){
    	boolean cond1 = (row >=0) && (row <= 7);
        boolean cond2 = (column >=0) && (column <= 7);
        if (!(cond1 && cond2)) {
            return false;
        }

    	if (!lvBoard.cells[row][column].isAccessible()) {
            return false;
        }

        if (subjectType.equals("hero")) {
            if (lvBoard.cells[row][column].hasHero()) {
                return false;
            }
        }

        if (subjectType.equals("monster")) {
            return !lvBoard.cells[row][column].hasMonster();
        }

        return true;
    }

    private void trade(Hero hero){
        Market market = new Market();
        boolean keepBuy = true;
        while (keepBuy) {
            //display
            InfoWindow marketAndSellWindow = new InfoWindow("market");
            ListWindow marketInfoWindow = new ListWindow(market.getClass().toString().split(" ")[1]);
            marketInfoWindow.setPosition(1,1);
            marketInfoWindow.addSubWidget(new BlankWidget(85,1+3,1, market.getDisplayLines()));

            marketAndSellWindow.addSubWidget(marketInfoWindow);

            ListWindow inventoryInfoWindow = new ListWindow(hero.getInventory().getClass().toString().split(" ")[1]);
            inventoryInfoWindow.setPosition(20,1);
            inventoryInfoWindow.addSubWidget(new BlankWidget(85,1+3,1,
                    hero.getInventory().getDisplayLines()));

            marketAndSellWindow.addSubWidget(inventoryInfoWindow);

            System.out.print(marketAndSellWindow);
            int totalNum = market.getTotalItemsNum();

            char input = Utils.safeCharInput("Please choose your action in the market window (buy, sell, exit)", new char[]{'b','s','e'});
            switch (input){
                case 'b':
                    int index = Utils.safeIntInput("Please select the merchandise to buy:",0,totalNum-1);
                    Merchandise merchandise = market.getItem(index);
                    //print Merchandise attributes by line
                    Window.newMessage("-----Merchandise's Info-----");
                    String merInfo = merchandise.getDisplayLines().toString();
                    for(String s : merInfo.substring(1, merInfo.length() - 1).split(", ")) {
                        Window.newMessage(s);
                    }
                    Window.newMessage("Are you sure to buy this merchandise?(y/others)");
                    System.out.print(marketAndSellWindow);
                    String check= scan.next();
                    if(check.equals("y")) {
                        if (!hero.buyMerchandise(merchandise))
                            Window.newMessage("Sorry hero " + hero.getName() + " does not meet the purchase conditions");
                        else
                            Window.newMessage("Hero " + hero.getName() + " got " + merchandise.getName());
                    }
                    break;
                case 's':
                    int NumEquipInInventory = hero.getInventory().getTotalItemsNum();
                    if(NumEquipInInventory == 0) {
                        Window.newMessage("Sorry, you don't have any merchandise.");
                        System.out.print(marketAndSellWindow);
                        break;
                    }
                    int indexOfEquipment = Utils.safeIntInput("Please select the merchandise to sell:",0,
                            NumEquipInInventory-1);
                    merchandise = hero.getInventory().getItem(indexOfEquipment);
                    //print Merchandise attributes by line
                    Window.newMessage("-----Merchandise's Info-----");
                    merInfo = merchandise.getDisplayLines().toString();
                    for(String s : merInfo.substring(1, merInfo.length() - 1).split(", ")) {
                        Window.newMessage(s);
                    }
                    Window.newMessage("Are you sure to sell this merchandise?(y/others)");
                    System.out.print(marketAndSellWindow);
                    check = scan.next();
                    if(check.equals("y")) {
                        hero.sellMerchandise(merchandise);
                        Window.newMessage("Hero " + hero.getName() + " sold " + merchandise.getName());
                    }
                    break;
                case 'e':
                    keepBuy = false;
                    break;
            }
        }
    }

    private void checkInfo(Hero h) {
        boolean keepCheck = true;
        while(keepCheck) {
            //print windows: Status, Inventory; manual: u.use/equip, r.remove, e.exit
            InfoWindow heroStatusWindow = new InfoWindow("status");
            ListWindow statusWindow = new ListWindow("Current Status");
            statusWindow.setPosition(1, 1);
            ArrayList<ArrayList<StringBuilder>> heroInfos = new ArrayList<>();
            heroInfos.add(h.getDisplayLines());
            heroInfos.add(new ArrayList<StringBuilder>());
            heroInfos.add(Hero.getGraphicLines());

            CardWiget heroInfo = new CardWiget(heroInfos);
            heroInfo.setPosition(1 + 3, 1);
            statusWindow.addSubWidget(heroInfo);

            heroStatusWindow.addSubWidget(statusWindow);

            ListWindow inventoryInfoWindow = new ListWindow(h.getInventory().getClass().toString().split(" ")[1]);
            inventoryInfoWindow.setPosition(20, 1);
            inventoryInfoWindow.addSubWidget(new BlankWidget(85, 1 + 3, 1, h.getInventory().getDisplayLines()));

            heroStatusWindow.addSubWidget(inventoryInfoWindow);

            //print
            System.out.print(heroStatusWindow);

            char input = Utils.safeCharInput("Please choose your action in the status/inventory window (use/equip, exit)",new char[]{'u','e'});
            switch (input) {
                case 'u':
                    int NumEquipInInventory = h.getInventory().getTotalItemsNum();
                    if(NumEquipInInventory == 0) {
                        Window.newMessage("Sorry, you don't have any merchandise.");
                        System.out.print(heroStatusWindow);
                        break;
                    }
                    int indexOfEquipment = Utils.safeIntInput("Please select the merchandise to equip or use:",0,
                            NumEquipInInventory-1);
                    Merchandise merchandise = h.getInventory().getItem(indexOfEquipment);
                    //print Merchandise attributes by line
                    Window.newMessage("-----Merchandise's Info-----");
                    String merInfo = merchandise.getDisplayLines().toString();
                    for(String s : merInfo.substring(1, merInfo.length() - 1).split(", ")) {
                        Window.newMessage(s);
                    }
                    Window.newMessage("Are you sure to equip or use this merchandise?(y/others)");
                    System.out.print(heroStatusWindow);
                    String check = scan.next();
                    if(check.equals("y")) {
                        h.equipOrUseMerchandise(merchandise);
                    }
                    break;
                case 'e':
                    keepCheck = false;
                    break;
            }
        }

    }

    //choose hero to join the team
    private void chooseHero() {
    	//scan hero files from config
    	ArrayList<Hero> heroesList = new ArrayList<>();
        String[] files = new String[]{"Warriors", "Sorcerers", "Paladins"};
        for(String file: files) {
            List<String> lines = new FileReader().readFile(file);
            for(int i = 1;i<lines.size();i++){
                if(lines.get(i).equals(""))
                    break;
                if(file.equals("Warriors"))
                    heroesList.add(new Warrior(lines.get(i).split("\\s+")));
                else if(file.equals("Sorcerers"))
                    heroesList.add(new Sorcerer(lines.get(i).split("\\s+")));
                else
                    heroesList.add(new Paladin(lines.get(i).split("\\s+")));
            }
        }
        //print the window for choosing heroes
        InfoWindow chooseHeroWindow = new InfoWindow("heroes");
        ListWindow heroDetailsWindow = new ListWindow("Heroes to Choose",37);
        heroDetailsWindow.setPosition(1,1);
        heroDetailsWindow.addSubWidget(new BlankWidget(85,1+3,1, Displayer.listDisplay(heroesList,"Heroes",0)));

        chooseHeroWindow.addSubWidget(heroDetailsWindow);
        //the required number of heroes in one team is 3
        Window.newMessage("First of all, let's build a team of 3 heroes:");
        int HeroNum = 3;
        for(int i=0; i<HeroNum; i++){
        	if(i!=0) {
        		Window.newMessage("Please select another hero you are interested in");
        	}
        	else {
            	Window.newMessage("Please select a hero you are interested in.");
                Window.newMessage("Input the number in front of the name.");
        	}
            System.out.print(chooseHeroWindow);
            int index = Utils.safeIntInput("Input: ", 0, heroesList.size());
            if(hTeam.getHeroes().contains(heroesList.get(index))) {
            	Window.newMessage("Hero " + heroesList.get(index).getName() +
                        " is already in the team, please select other heroes.");
                System.out.print(chooseHeroWindow);
                i--;
            }
            else {
            	//print hero attributes by line
                Window.newMessage("-----Hero's Info-----");
            	String heroString = heroesList.get(index).getDisplayLines().toString();
            	for(String s : heroString.substring(1, heroString.length() - 1).split(", ")) {
                    Window.newMessage(s);
            	}
            	Window.newMessage("Do you want hero "+ heroesList.get(index).getName() + " to join your team?(y/n)");
                System.out.print(chooseHeroWindow);

                char[] allowedChars = {'y', 'n'};
                char userCharInput = Utils.safeCharInput("Input: ", allowedChars);
                if(userCharInput == 'y')
                	hTeam.addHero(heroesList.get(index));
                else
                    i--;
            }
        }
    }

    private Monster chooseMonster(ArrayList<Characters> targetList){
        ArrayList<ArrayList<StringBuilder>> monstersInfo = new ArrayList<>();
        // select target
        Window.newMessage("Please select a target:");
        for (int idx = 0; idx < targetList.size(); idx++) {
            ArrayList<StringBuilder> monsterInfo = targetList.get(idx).getDisplayLines();
            monsterInfo.add(0,new StringBuilder("Target " + idx ));
            monstersInfo.add(monsterInfo);
        }
        InfoWindow MonsterWindow = new InfoWindow("monster");
        ListWindow MonsterInfoWindow = new ListWindow("Monster",37);
        MonsterInfoWindow.setPosition(1,1);
        CardWiget monsterInfoWidget = new CardWiget(monstersInfo);
        monsterInfoWidget.setPosition(1 + 3, 1);
        MonsterInfoWindow.addSubWidget(monsterInfoWidget);
        MonsterWindow.addSubWidget(MonsterInfoWindow);
        System.out.println(MonsterWindow);
        return (Monster)targetList.get(Utils.safeIntInput("Input: ", 0, targetList.size() - 1));
    }


    @Override
    protected void quitGame() {
        Window exitWindow = new ExitWindow();
        System.out.print(exitWindow);
        System.exit(0);
    }
}
