import java.util.ArrayList;
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
        System.out.println("--------------Welcome to Legends of Valor--------------");
        //generate monster, hero team, monster team
    	//pick hero phase, choose 3
        System.out.println("\nFirst of all, let's build a team of 3 heroes:");
        hTeam.chooseHero();
        
        //TODO method: print team info
        System.out.print("You built a Team of ");

        hTeam.displayHerosName();
        mTeam.displayMonstersName();
        
        printInstruction();
		//print map,
		
		//start 
		//round start
			//turn start, iterate the arrayList of heroes
			//hero 1 move
				//buy at market
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
            for(Hero h : hTeam.getHeroes()) {
	            mahBoard.display(h.getRow(), h.getColumn());
	            chooseAction(h);
	        	
            
	            
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



    //TODO update with new UI
    private void printInstruction(){
        System.out.println("+---------------------------------------+");
        System.out.println("|Move: w a s d; Information: i; Quit: q |");
        System.out.println("|H: Heroes;  N: inaccessible;  M: Market|");
        System.out.println("|C: Wild                                |");
        System.out.println("+---------------------------------------+");
    }

    //choose the action for each of heroes
    private void chooseAction(Hero h){
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
        int input = Tools.intScanner(1,9);
        switch(input) {
        case 1:
            System.out.println("Please choose your direction:");
            while(true) {
	            char direction = Tools.charScanner("wasd");
	            if(direction == 'w'){
	                if(checkMove(h.getRow()-1, h.getColumn())){
	                    h.move(h.getRow()-1, h.getColumn());
	                    hTeam.updateHighest();
	                    break;
	                }
	                else
	                    System.out.println("The place is inaccessible. Please try again.");
	            }
	            else if(direction == 's'){
	                if(checkMove(h.getRow()+1, h.getColumn())){
	                    h.move(h.getRow()+1, h.getColumn());
	                    break;
	                }
	                else
	                    System.out.println("The place is inaccessible. Please try again.");
	            }
	            else if(direction == 'a'){
	                if(checkMove(h.getRow(), h.getColumn()-1)){
	                    h.move(h.getRow(), h.getColumn()-1);
	                    break;
	                }
	                else
	                    System.out.println("The place is inaccessible. Please try again.");
	            }
	            else if(direction == 'd'){
	                if(checkMove(h.getRow(), h.getColumn()+1)){
	                    h.move(h.getRow(), h.getColumn()+1);
	                    break;
	                }
	                else
	                    System.out.println("The place is inaccessible. Please try again.");
	            }
            }
        	break;
        //check info
        case 2:
            ArrayList<ArrayList<StringBuilder>> stringBuilder = new ArrayList<>();
            for(Hero hero:hTeam.getHeroes())
                stringBuilder.add(hero.getDisplayLines());
            Displayer.formDisplay(stringBuilder,3,30);
            h.checkInfo();
            chooseAction(h);
        	break;
        //buy
        case 3:
        	//TODO new market
        	Market market = ((MarketCell)mahBoard.getCells()[h.getRow()][h.getColumn()]).getMarket();
            //trade
            trade(market);
        	break;
        //print map
        case 4:
            mahBoard.display(h.getRow(), h.getColumn());
            chooseAction(h);
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
            int r = Tools.intScanner(1,8)-1;
            System.out.println("Column:");
            int c = Tools.intScanner(1,8)-1;
            //TODO check monster
            if(!checkMove(r, c)){
                System.out.println("The place is inaccessible.");
                chooseAction(h);
            }
            else {
            	if(r>=hTeam.getHighest()) {
                	h.move(r, c);
            	}
            	else {
            		System.out.println("This hero can't teleport to a location higher than previous highest record");
                    chooseAction(h);
            	}
            }
        	break;
        //Back to Nexus
        case 8:
        	h.setRow(7);
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
    	System.out.printf("%s' turn end.\n^*^*^*^*^*^*^*^*^*^*^*^*^*^\n", h.getName());
    }

    //check whether the new cell is accessible
    private boolean checkMove(int row, int column){
    	//check map resitriction
        if(row>=0 && column>=0 && mahBoard.getCells()[row][column].isAccessible() && row<=7 && column <=7 )
        	//check monster position
        	//if()
            return true;
        
        else 
        	return false;
        //TODO check monster
    }

    /*
    //trade with a market
    private void trade(Market market){
    	//TODO new market class
        int totalNum = market.display();
        while (true) {
            System.out.println("Do you want to check one of the merchandises in the market?(y/others)");
            String input = scan.next();
            if(input.equals("y")) {
                int index = Displayer.chooseList(totalNum);
                Merchandise merchandise = market.displayItems(index);
                System.out.println("Do you want to buy this merchandise for one of your heros?(y/others)");
                input = scan.next();
                if(input.equals("y")){
                    System.out.println("Which hero wants to buy equipments?");
                    team.displayHerosName();
                    int indeOfHero = Displayer.chooseList(team.getHeroes().size());
                    Hero hero = team.getHeroes().get(indeOfHero);
                    if(!hero.buyMerchandise(merchandise))
                        System.out.println("Sorry hero " + hero.getName()+" does not meet the purchase conditions");
                    else
                        System.out.println("Hero " + hero.getName() + " got " + merchandise.getName());
                }
            }
            else break;
        }
        while (true) {
            System.out.println("Do you want to sell equipments?(y/others)");
            String input = scan.next();
            if(input.equals("y")) {
                System.out.println("Which hero wants to sell his/her equipment?");
                team.displayHerosName();
                int indeOfHero = Displayer.chooseList(team.getHeroes().size());
                Hero hero = team.getHeroes().get(indeOfHero);
                int NumEquipInInventory = hero.getInventory().display();
                int indexOfEquipment = Displayer.chooseList(NumEquipInInventory);
                Merchandise merchandise = hero.getInventory().displayItems(indexOfEquipment);
                System.out.println("Do you want to sell this merchandise?(y/others)");
                input = scan.next();
                if(input.equals("y")){
                    hero.sellMerchandise(merchandise);
                }
            }
            else break;
        }
    }*/

    private void trade(Market market){
    	//TODO new market class design
        int totalNum = market.display();
        while (true) {
            int index = Displayer.chooseList(totalNum);
            Merchandise merchandise = market.displayItems(index);
            System.out.println("Do you want to buy this merchandise for this hero?(y/others)");
            String input = scan.next();
            if(input.equals("y")){
                System.out.println("Which hero wants to buy equipments?");
                hTeam.displayHerosName();
                int indeOfHero = Displayer.chooseList(hTeam.getHeroes().size());
                Hero hero = hTeam.getHeroes().get(indeOfHero);
                if(!hero.buyMerchandise(merchandise))
                    System.out.println("Sorry hero " + hero.getName()+" does not meet the purchase conditions");
                else
                    System.out.println("Hero " + hero.getName() + " got " + merchandise.getName());

            System.out.println("Do you want to check other merchandises in the market?(y/n)");
            char in = Tools.charScanner("yn");
            if(in == 'y') 
            	continue;
            else
            	break;
        }
        while (true) {
            System.out.println("Which hero wants to sell his/her equipment?");
            hTeam.displayHerosName();
            int indeOfHero = Displayer.chooseList(hTeam.getHeroes().size());
            Hero hero = hTeam.getHeroes().get(indeOfHero);
            int NumEquipInInventory = hero.getInventory().display();
            int indexOfEquipment = Displayer.chooseList(NumEquipInInventory);
            Merchandise merchandise = hero.getInventory().displayItems(indexOfEquipment);
            System.out.println("Do you want to sell this merchandise?(y/others)");
            input = scan.next();
            if(input.equals("y")){
                hero.sellMerchandise(merchandise);
            }
            System.out.println("Do you want to check other merchandises in the market?(y/n)");
            char in = Tools.charScanner("yn");
            if(in == 'y') 
            	continue;
            else
            	break;
        	}
        }
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

}
