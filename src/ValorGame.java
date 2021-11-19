import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//the entity of game monster and hero
public class ValorGame extends RPGGame{
    private LVBoard mahBoard;
    //private Team team;
    private Scanner scan;

    private int roundCounter=0;
	private MonsterTeam mTeam;
	private HeroTeam hTeam;

    public ValorGame(){
       scan = new Scanner(System.in);
       //TODO change class name
       mahBoard = new LVBoard(8);
       hTeam = new HeroTeam();
       mTeam = new MonsterTeam();
    }

    //game start
    public void start(){
    	Window welcomeWindow = new WelcomeWindow();
    	welcomeWindow.newMessage("Welcome to Legends of Valor!");
        System.out.println(welcomeWindow);
        scan.nextLine();
    	//pick hero phase, choose 3
        chooseHero();
        Window combatWindow = new CombatWindow();
        
        //TODO A method to print info of two teams & put messages into log
        System.out.print("You have built a Team of ");
        hTeam.displayHerosName();
        mTeam.displayMonstersName();
        
		//start
        LVBoard theBoard = ((CombatWindow)combatWindow).getTheBoard();
        //initiate hero location
        for(int i=0,j=0; i<=8; i+=3,j++) {
            theBoard.cells[7][i].slot1Arrive(j);
            hTeam.getHeroes().get(j).move(7, i);
        }
		//round start
			//turn start, iterate the arrayList of heroes
			//hero 1 move
			//hero 2
			//hero 3
			//monsters' turns
			//iterate for each monsters to forward/attack

			//next round, round timer for add new monsters
    		//print hero team status, monster team status


        while (roundCounter<6) {
            //display the map
        	roundCounter++;
        	System.out.printf("This is round %d, Hero Action Phase:\n", roundCounter);
            System.out.println(combatWindow);
            for(Hero h : hTeam.getHeroes()) {
	            chooseAction(h, theBoard, (CombatWindow)combatWindow);

	            /* previous engagement check
	            if(action.equals("move")){
	                //move to a market cell
	                if(mahBoard.getCells()[h.getRow()][h.getColumn()] instanceof MarketCell){
	                    System.out.println("WELCOME TO MARKET!!!");
	                    Market market = ((MarketCell)mahBoard.getCells()[team.getRow()][team.getColumn()]).getMarket();
	                    //trade
	                    trade(market);
	                }
	                //move to common cell
	                else {
	                    //whether meet monsters
	                    if(((CommenCell)mahBoard.getCells()[team.getRow()][team.getColumn()]).isMonster()){
	                        System.out.println("HERO MEET MONSTERS!!!");
	                        fightManager = new FightManager(team);
	                        fightManager.start();
	                    }
	                    else
	                        System.out.println("LUCKY!!! NO MONSTERS!!!");
	                }
	            }
	            */
            }
            System.out.println("Hero Phase end. Monster Action Phase:");
            for(Monster m : mTeam.getMonsters()) {
            	//if no target in range, then move forward:
            	if(false) {
            		continue;
            	}
            	else {
            		m.setRow(m.getRow()+1);
            	}
            }
        	System.out.printf("End of round %d!\n^*^*^*^*^*^*^*^*^*^*^*^*^*^\n", roundCounter);

            Displayer.listDisplay(hTeam.getHeroes(),"Heroes",0);
        }

    }


    //choose the action for each of heroes
    private void chooseAction(Hero h, LVBoard b, CombatWindow c){
    	System.out.printf("This is %s' turn, ", h.getName());
        System.out.println("please choose an action from below:\n"
        		+ "1. Move\n"
        		+ "2. Check Hero Info\n"
        		+ "3. Buy\n"
        		+ "4. Check Map\n"
        		+ "5. Attack\n"
        		+ "6. Cast Spell\n"
        		+ "7. Teleport\n"
        		+ "8. Back to Nexus\n"
        		+ "9. End Turn\n"
        		+ "0. Quit Game");
        int input = Tools.intScanner(0,9);
        switch(input) {
        case 1:
            System.out.println("Please choose your direction:");
            char direction = Tools.charScanner("wasd");
            if(direction == 'w'){
                if(checkMove(h.getRow()-1, h.getColumn(), b)){
                	//check slot availability
                	if (b.cells[h.getRow()-1][h.getColumn()].slot1.trim().isEmpty()) {
                		//arrive, leave
                    	b.cells[h.getRow()][h.getColumn()].slot1Leave();
	                    h.move(h.getRow()-1, h.getColumn());
                    	b.cells[h.getRow()][h.getColumn()].slot1Arrive(hTeam.getHeroes().indexOf(h));
                    	//update highest record
	                    hTeam.updateHighest();
	                    break;
                    }
                    else if(b.cells[h.getRow()-1][h.getColumn()].slot2.trim().isEmpty()) {
                		//arrive, leave
                    	b.cells[h.getRow()][h.getColumn()].slot2Leave();
	                    h.move(h.getRow()-1, h.getColumn());
                    	b.cells[h.getRow()][h.getColumn()].slot2Arrive(hTeam.getHeroes().indexOf(h));
	                    hTeam.updateHighest();
	                    break;
                    }
                    else {
                    	System.out.println("The slots of this cell are full. Please try again.");
                    }
                }
                else
                    System.out.println("The place is inaccessible. Please try again.");
            }
            else if(direction == 's'){
                if(checkMove(h.getRow()+1, h.getColumn(), b)){
                	//check slot availability
                	if (b.cells[h.getRow()+1][h.getColumn()].slot1.trim().isEmpty()) {
                    	b.cells[h.getRow()][h.getColumn()].slot1Leave();
	                    h.move(h.getRow()+1, h.getColumn());
                    	b.cells[h.getRow()][h.getColumn()].slot1Arrive(hTeam.getHeroes().indexOf(h));
                        break;
                    }
                    else if(b.cells[h.getRow()+1][h.getColumn()].slot2.trim().isEmpty()) {
                    	b.cells[h.getRow()][h.getColumn()].slot2Leave();
	                    h.move(h.getRow()+1, h.getColumn());
                    	b.cells[h.getRow()][h.getColumn()].slot2Arrive(hTeam.getHeroes().indexOf(h));
                        break;
                    }
                    else {
                    	System.out.println("The slots of this cell are full. Please try again.");
                    }
                }
                else
                    System.out.println("The place is inaccessible. Please try again.");
            }
            else if(direction == 'a'){
                if(checkMove(h.getRow(), h.getColumn()-1, b)){
                	//check slot availability
                	if (b.cells[h.getRow()][h.getColumn()-1].slot1.trim().isEmpty()) {
                    	b.cells[h.getRow()][h.getColumn()].slot1Leave();
	                    h.move(h.getRow(), h.getColumn()-1);
                    	b.cells[h.getRow()][h.getColumn()].slot1Arrive(hTeam.getHeroes().indexOf(h));
                        break;
                    }
                    else if(b.cells[h.getRow()][h.getColumn()-1].slot2.trim().isEmpty()) {
                    	b.cells[h.getRow()][h.getColumn()].slot2Leave();
	                    h.move(h.getRow(), h.getColumn()-1);
                    	b.cells[h.getRow()][h.getColumn()].slot2Arrive(hTeam.getHeroes().indexOf(h));
                        break;
                    }
                    else {
                    	System.out.println("The slots of this cell are full. Please try again.");
                    }
                }
                else
                    System.out.println("The place is inaccessible. Please try again.");
            }
            else if(direction == 'd'){
                if(checkMove(h.getRow(), h.getColumn()+1, b)){
                	//check slot availability
                	if (b.cells[h.getRow()][h.getColumn()+1].slot1.trim().isEmpty()) {
                    	b.cells[h.getRow()][h.getColumn()].slot1Leave();
	                    h.move(h.getRow(), h.getColumn()+1);
                    	b.cells[h.getRow()][h.getColumn()].slot1Arrive(hTeam.getHeroes().indexOf(h));
                        break;
                    }
                    else if(b.cells[h.getRow()][h.getColumn()+1].slot2.trim().isEmpty()) {
                    	b.cells[h.getRow()][h.getColumn()].slot2Leave();
	                    h.move(h.getRow(), h.getColumn()+1);
                    	b.cells[h.getRow()][h.getColumn()].slot2Arrive(hTeam.getHeroes().indexOf(h));
                        break;
                    }
                    else {
                    	System.out.println("The slots of this cell are full. Please try again.");
                    }
                }
                else
                    System.out.println("The place is inaccessible. Please try again.");
            }
            chooseAction(h, b, c);
        	break;
        	
        //check info
        case 2:
        	/*
            ArrayList<ArrayList<StringBuilder>> stringBuilder = new ArrayList<>();
            for(Hero hero:hTeam.getHeroes())
                stringBuilder.add(hero.getDisplayLines());
            Displayer.formDisplay(stringBuilder,3,30);
            */
            checkInfo(h);
            chooseAction(h, b, c);
        	break;
        //buy
        case 3:
        	//new market
            if(mahBoard.getCells()[h.getRow()][h.getColumn()] instanceof NexusCell) {
                //trade
                trade(h);
                chooseAction(h, b, c);
            }
            else{
            	System.out.println("You are not in nexus, you cannot buy anything!");
                chooseAction(h, b, c);
            }
        	break;
        //print map
        case 4:
        	System.out.println(c);
            chooseAction(h, b, c);
        	break;
        //Attack
        case 5:

        	break;
        //Cast spell
        case 6:

        	break;
        //Teleport
        case 7:
            System.out.println("You are teleporting to:");
            System.out.println("Row:");
            int row = Tools.intScanner(1,8)-1;
            System.out.println("Column:");
            int column = Tools.intScanner(1,8)-1;
            //TODO check monster
            if(!checkMove(row, column, b)){
                System.out.println("The place is inaccessible.");
                chooseAction(h, b, c);
            }
            else {
            	//check highest
            	if(row>=hTeam.getHighest()) {
                	//check slot availability
                	if (b.cells[row][column].slot1.trim().isEmpty()) {
                    	b.cells[h.getRow()][h.getColumn()].slot1Leave();
                        h.move(row, column);
                    	b.cells[h.getRow()][h.getColumn()].slot1Arrive(hTeam.getHeroes().indexOf(h));
                    }
                    else if(b.cells[row][column].slot2.trim().isEmpty()) {
                    	b.cells[h.getRow()][h.getColumn()].slot2Leave();
                        h.move(row, column);
                    	b.cells[h.getRow()][h.getColumn()].slot2Arrive(hTeam.getHeroes().indexOf(h));
                    }
                	else {
                    	System.out.println("The slots of this cell are full. Please try again.");
                        chooseAction(h, b, c);
                	}
            	}
            	else {
            		System.out.println("This hero can't teleport to a location higher than previous highest record");
                    chooseAction(h, b, c);
            	}
            }
        	break;
        //Back to Nexus
        case 8:
        	if (b.cells[7][h.getColumn()].slot1.trim().isEmpty()) {
            	b.cells[h.getRow()][h.getColumn()].slot1Leave();
                h.move(7, h.getColumn());
            	b.cells[h.getRow()][h.getColumn()].slot1Arrive(hTeam.getHeroes().indexOf(h));
            }
            else if(b.cells[7][h.getColumn()].slot2.trim().isEmpty()) {
            	b.cells[h.getRow()][h.getColumn()].slot2Leave();
                h.move(7, h.getColumn());
            	b.cells[h.getRow()][h.getColumn()].slot2Arrive(hTeam.getHeroes().indexOf(h));
            }
        	else {
            	System.out.println("The slots of this cell are full. Please try again.");
                chooseAction(h, b, c);
        	}
        	break;
        //End turn
        case 9:
        	break;
        //Quit
        case 0:
            System.out.println("Quit game.");
            System.exit(0);
        	break;

        }
    	System.out.printf("%s' turn ends.\n^*^*^*^*^*^*^*^*^*^*^*^*^*^\n", h.getName());
    }

    //check whether the new cell is accessible
    private boolean checkMove(int row, int column, LVBoard b){
    	//check map resitriction, 
    	boolean bo= b.cells[row][column].isAccessible();
    	System.out.printf("row%d, column%d, %b", row, column, bo);
        if(row>=0 && column>=0 && b.cells[row][column].isAccessible() && row<=7 && column <=7 )
	        //TODO check monster
            return true;

        else
        	return false;
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

            System.out.println(marketAndSellWindow);
            int totalNum = market.getTotalItemsNum();

            char input = Tools.charScanner("bse");
            switch (input){
                case 'b':
                    int index = Utils.safeIntInput("Please select the merchandise to buy:",0,totalNum-1);
                    Merchandise merchandise = market.getItem(index);
                    marketAndSellWindow.newMessage(merchandise.getDisplayLines().toString());
                    System.out.println(marketAndSellWindow);
                    System.out.println("Are you sure to buy this merchandise?(y/others)");
                    String check= scan.next();
                    if(check.equals("y")) {
                        if (!hero.buyMerchandise(merchandise))
                            marketAndSellWindow.newMessage("Sorry hero " + hero.getName() + " does not meet the purchase conditions");
                        else
                            marketAndSellWindow.newMessage("Hero " + hero.getName() + " got " + merchandise.getName());
                    }
                    break;
                case 's':
                    int NumEquipInInventory = hero.getInventory().getTotalItemsNum();
                    if(NumEquipInInventory == 0) {
                        marketAndSellWindow.newMessage("Sorry, you don't have any merchandise.");
                        System.out.println(marketAndSellWindow);
                        break;
                    }
                    int indexOfEquipment = Utils.safeIntInput("Please select the merchandise to sell:",0,
                            NumEquipInInventory-1);
                    merchandise = hero.getInventory().getItem(indexOfEquipment);
                    marketAndSellWindow.newMessage(merchandise.getDisplayLines().toString());
                    System.out.println(marketAndSellWindow);
                    System.out.println("Are you sure to sell this merchandise?(y/others)");
                    check = scan.next();
                    if(check.equals("y")) {
                        hero.sellMerchandise(merchandise);
                        marketAndSellWindow.newMessage("Hero " + hero.getName() + " sold " + merchandise.getName());
                    }
                    break;
                case 'e':
                    keepBuy = false;
                    break;
            }
        }
    }

    private void checkInfo(Hero h) {
    	//print windows: Status, Inventory; manual: u.use/equip, r.remove, e.exit 
        InfoWindow heroStatusWindow = new InfoWindow("status");
        ListWindow statusWindow = new ListWindow("Current Status");
        statusWindow.setPosition(1,1);
        statusWindow.addSubWidget(new BlankWidget(85,1+3,1, h.getDisplayString()));

        heroStatusWindow.addSubWidget(statusWindow);

        ListWindow inventoryInfoWindow = new ListWindow(h.getInventory().getClass().toString().split(" ")[1]);
        inventoryInfoWindow.setPosition(20,1);
        inventoryInfoWindow.addSubWidget(new BlankWidget(85,1+3,1, h.getInventory().getDisplayLines()));

        heroStatusWindow.addSubWidget(inventoryInfoWindow);

    	//print
    	System.out.print(heroStatusWindow);
    }
    //change equipment and use potions when not fighting
    /*TODO each type for an array list. In case of spells nested arrayList but print "spell:"?
    private void equipHero(){
        while (true) {
            System.out.println("Do you want to check heroes' inventory?(y/others)");
            String input = scan.next();
            if(input.equals("y")) {
                System.out.println("Which heroes' inventory do you want to check?");
                team.displayHerosName();
                int indeOfHero = Displayer.chooseList(team.getHeroes().size());
                }
                Hero hero = team.getHeroes().get(indeOfHero);
                int NumEquipInInventory = hero.getInventory().display();
                if(NumEquipInInventory==0)
                    System.out.println("Hero " + hero.getName() + " doesn't have any equipment.");
                else {
                    System.out.println("Does hero "+ hero.getName()+" want to change his/her equipment or use potions?(y/n)");
                    input = scan.next();
                    if(input.equals("y")) {
                        System.out.println("Select the equipment hero want to equip or use.");
                        int indexOfEquipment = Displayer.chooseList(NumEquipInInventory);
                        Merchandise merchandise = hero.getInventory().displayItems(indexOfEquipment);
                        System.out.println("Do you want to equip or use this merchandise?(y/others)");
                        input = scan.next();
                        if (input.equals("y")) {
                            hero.equipOrUseMerchandise(merchandise);
                        }
                    }
                }
            }
            else break;
        }
    }
	*/
    
    //choose hero to join the team
    public void chooseHero(){
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
        ListWindow heroDetailsWindow = new ListWindow("Heroes to Choose");
        heroDetailsWindow.setPosition(1,1);
        heroDetailsWindow.addSubWidget(new BlankWidget(85,1+3,1, Displayer.listDisplay(heroesList,"Heroes",0)));

        chooseHeroWindow.addSubWidget(heroDetailsWindow);
        //the required number of heroes in one team is 3
        chooseHeroWindow.newMessage("First of all, let's build a team of 3 heroes:");
        int HeroNum = 3;
        for(int i=0; i<HeroNum; i++){
        	if(i!=0) {
        		chooseHeroWindow.newMessage("Please select another hero you are interested in");
        	}
        	else {
            	chooseHeroWindow.newMessage("Please select a hero you are interested in.(input the number in front of the item)");
        	}
            System.out.print(chooseHeroWindow);
            int index = Tools.intScannerInWindow(0, heroesList.size(), chooseHeroWindow);
            if(hTeam.getHeroes().contains(heroesList.get(index))) {
            	chooseHeroWindow.newMessage("Hero " + heroesList.get(index).getName() +
                        " is already in the team, please select other heroes.");
                System.out.print(chooseHeroWindow);
                i--;
            }
            else {
            	//print hero attributes by line
                chooseHeroWindow.newMessage("-----Hero's Info-----");
            	String heroString = heroesList.get(index).getDisplayLines().toString();
            	for(String s : heroString.substring(1, heroString.length() - 1).split(", ")) {
                    chooseHeroWindow.newMessage(s);
            	}
            	chooseHeroWindow.newMessage("Do you want hero "+ heroesList.get(index).getName() + " to join your team?(y/others)");
                System.out.print(chooseHeroWindow);
                String input = scan.next();
                if(input.equals("y"))
                	hTeam.addHero(heroesList.get(index));
                else
                    i--;
            }
        }
    }
}
