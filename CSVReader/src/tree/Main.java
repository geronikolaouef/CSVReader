package tree;

import java.io.BufferedReader;
import java.io.File; 
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{       
        (new Utilities()).openMenu();

        System.out.println("**END OF PROGRAMM**");
	}    
}

class Utilities
{
    boolean fileLoaded = false;

    void openMenu()
    {      
        ArrayList<Person> persons = new ArrayList<>(); 

        Scanner scanner = new Scanner(System.in);
        String input = "";
        String sinput = "";
        String path = "";
        boolean found = false;
        boolean sfound = false;        

        do
        {
            System.out.println("|========================================= Menu ===========================================|");
            System.out.println("| 1: Load a file of csv type.                                                              |");
            System.out.println("| 2: Create a file of txt type with the sorted list and save it to the computer's desktop. |");
            System.out.println("| 3: Insert two names to find the connectio between them.                                  |");
            System.out.println("| 4: Exit.                                                                                 |");
            System.out.println("| Please select one of the options above.                                                  |");
            System.out.println("|==========================================================================================|");

            input = scanner.nextLine();

            if (input.equals("1"))
            {
                System.out.println("Do you wish to search in a new file? .. y/n");               
                input = scanner.nextLine();
                while ((!(input.equalsIgnoreCase("y")))&&(!(input.equalsIgnoreCase("n"))))
                {
                    System.out.println("Please insert a viable answer... y/n");
                    input = scanner.nextLine();
                }
                if (input.equalsIgnoreCase("y"))
                {
                    System.out.println("Please insert the filepath you wish to search...");
                    path = scanner.nextLine();
                }
                else if (input.equalsIgnoreCase("n"))
                {
                    while(path.equals(""))
                    {
                        System.out.println("Cannot search on an empty path...");
                        System.out.println("Please insert a viable path...");
                        path = scanner.nextLine();
                    }
                }
                System.out.println("Loading " + path + " ...");
                persons = readFile(path);                
            }
            else if (input.equals("2"))
            {
                if (fileLoaded)
                {
                    System.out.println("Processing file on the existing path...");
                    writeToFile(sortList(persons), "C:/Users/Stathis/Desktop/list.txt");
                } 
                else if (!fileLoaded)
                {
                    System.out.println("Please load a file!");
                }
            }   
            else if (input.equals("3"))
            {
                if (fileLoaded)
                {
                    System.out.println("Please insert the first name...");
                    input = scanner.nextLine();
                    for (Person person : persons)
                    {
                        if (person.getName().equalsIgnoreCase(input))
                        {
                            found = true;
                            break;
                        }   
                        else if (!(person.getName().equalsIgnoreCase(input)))
                        {
                            found = false;
                        }
                    }
                    while (!found)
                    {
                        System.out.println("The name you inserted does not exist in the given file!");
                        System.out.println("Please insert a viable name!");
                        input = scanner.nextLine();
                        for (Person person : persons)
                        {
                            if (person.getName().equalsIgnoreCase(input))
                            {
                                found = true;
                                break;
                            }   
                            else if (!(person.getName().equalsIgnoreCase(input)))
                            {
                                found = false;
                            }
                        }
                    }
                    System.out.println("Please insert the second name...");
                    sinput = scanner.nextLine();               
                    if (sinput.equalsIgnoreCase(input))
                    {
                        System.out.println("You can not enter the same name twice!");
                        while (sinput.equalsIgnoreCase(input))
                        {
                            System.out.println("Please insert a viable name!");
                            sinput = scanner.nextLine();
                        }
                    }                
                    for (Person person : persons)
                    {
                        if (person.getName().equalsIgnoreCase(sinput))
                        {
                            sfound = true;
                            break;
                        }   
                        else if (!(person.getName().equalsIgnoreCase(sinput)))
                        {
                            sfound = false;
                        }
                    }
                    while (!sfound)
                    {
                        System.out.println("The name you inserted does not exist in the given file!");
                        System.out.println("Please insert a viable name!");
                        sinput = scanner.nextLine();
                        for (Person person : persons)
                        {
                            if (person.getName().equalsIgnoreCase(sinput))
                            {
                                sfound = true;
                                break;
                            }  
                            else if (!(person.getName().equalsIgnoreCase(sinput)))
                            {
                                sfound = false;
                            } 
                        }
                    }
                    if ((found)&&(sfound))
                    {
                        findConnection(input, sinput, persons);
                    }    
                } 
                else if (!fileLoaded)
                {
                    System.out.println("Please load a file!");
                }                            
            }        
        } 
        while (!(input.equals("4")));
    }

    void findConnection(String input, String sinput, ArrayList<Person> persons)
    {
        boolean foundConnection = false;

        for (Person person : persons)
        {
            if (input.equalsIgnoreCase(person.getName()))
            {
                if (person.getSpouse().equalsIgnoreCase(sinput))
                {
                    if (person.getGender().equals("man"))
                    {
                        System.out.println(person.getName() + " is " + sinput + "'s husband.");
                        foundConnection = true;
                    }
                    else
                    {
                        System.out.println(person.getName() + " is " + sinput + "'s wife.");
                        foundConnection = true;
                    }                    
                }
                else if ((person.getFather().equalsIgnoreCase(sinput))||(person.getMother().equalsIgnoreCase(sinput)))
                {
                    if (person.getGender().equals("man"))
                    {
                        System.out.println(person.getName() + " is " + sinput + "'s son.");
                        foundConnection = true;
                    }
                    else
                    {
                        System.out.println(person.getName() + " is " + sinput + "'s daughter.");
                        foundConnection = true;
                    }                   
                }
                else if ((person.getGrandfather().equalsIgnoreCase(sinput))||(person.getGrandmother().equalsIgnoreCase(sinput)))
                {
                    if (person.getGender().equals("man"))
                    {
                        System.out.println(person.getName() + " is " + sinput + "'s grandson.");
                        foundConnection = true;
                    }
                    else
                    {
                        System.out.println(person.getName() + " is " + sinput + "'s granddaughter.");
                        foundConnection = true;
                    }                    
                }
                for (String s : person.getUncle())
                {
                    if (s.equalsIgnoreCase(sinput))
                    {
                        if (person.getGender().equals("man"))
                        {
                            System.out.println(person.getName() + " is " + sinput + "'s nephew");
                            foundConnection = true;
                        }
                        else
                        {
                            System.out.println(person.getName() + " is " + sinput + "'s niece");
                            foundConnection = true;
                        }  
                        break;                                            
                    }
                }
                for (String s : person.getAunt())
                {
                    if (s.equalsIgnoreCase(sinput))
                    {
                        if (person.getGender().equals("man"))
                        {
                            System.out.println(person.getName() + " is " + sinput + "'s nephew");
                            foundConnection = true;
                        }
                        else
                        {
                            System.out.println(person.getName() + " is " + sinput + "'s niece");
                            foundConnection = true;
                        }
                        break;                        
                    }
                }
                for (String s : person.getSiblings())
                {
                    if (s.equalsIgnoreCase(sinput))
                    {
                        if (person.getGender().equals("man"))
                        {
                            System.out.println(person.getName() + " is " + sinput + "'s brother");
                            foundConnection = true;
                        }
                        else
                        {
                            System.out.println(person.getName() + " is " + sinput + "'s sister");
                            foundConnection = true;
                        }
                        break;                        
                    }
                }
                for (String s : person.getNephews())
                {
                    if (s.equalsIgnoreCase(sinput))
                    {
                        if (person.getGender().equals("man"))
                        {
                            System.out.println(person.getName() + " is " + sinput + "'s uncle");
                            foundConnection = true;
                        }
                        else
                        {
                            System.out.println(person.getName() + " is " + sinput + "'s aunt");
                            foundConnection = true;
                        }
                        break;                        
                    }
                }
                for (String s : person.getCousins())
                {
                    if (s.equalsIgnoreCase(sinput))
                    {
                        System.out.println(person.getName() + " is " + sinput + "'s cousin");
                        foundConnection = true;
                        break;                        
                    }
                }
                for (String s : person.getGrandchildren())
                {
                    if (s.equalsIgnoreCase(sinput))
                    {
                        if (person.getGender().equals("man"))
                        {
                            System.out.println(person.getName() + " is " + sinput + "'s grandfather");
                            foundConnection = true;
                        }
                        else
                        {
                            System.out.println(person.getName() + " is " + sinput + "'s grandmother");
                            foundConnection = true;
                        }
                        break;                        
                    }
                }
                for (String s : person.getChildren())
                {
                    if (s.equalsIgnoreCase(sinput))
                    {
                        if (person.getGender().equals("man"))
                        {
                            System.out.println(person.getName() + " is " + sinput + "'s father");
                            foundConnection = true;
                        }
                        else
                        {
                            System.out.println(person.getName() + " is " + sinput + "'s mother");
                            foundConnection = true;
                        }
                        break;                        
                    }
                }                
            }            
        }
        if (foundConnection)
        {
            foundConnection = false;
        }
        else
        {
            System.out.println(input + " and " + sinput + " are not related.");
        }
    }

    ArrayList<Person> readFile(String path)
    {
        ArrayList<Person> persons = new ArrayList<>();	
        try
		{
			List< List<String> > data = new ArrayList<>();
			String file = path;
			FileReader fr = new FileReader(file);
            System.out.println("File loaded succesfully!");
            fileLoaded = true;
			BufferedReader br = new BufferedReader(fr);
            String name = "";
            String gender = "";
            String spouse = "";                                 
            ArrayList<String> children = new ArrayList<>();	            		
			
			String line = br.readLine();
			while(line != null)
			{
				List<String> lineData = Arrays.asList(line.split(","));
				data.add(lineData);
				line = br.readLine();
			}	          
            
            int counterIn = 0;  
            int counterOut = 0; 
           
			for(List<String> list : data)
			{             
                if ((counterOut != 0)&&(list.size() == 2))
                {                                                                             
                    persons.add(new Person(name, gender, spouse, children));  
                    name = "";
                    gender = "";
                    spouse = "";      
                    children = new ArrayList<>();                                                                     
                }  

				for(String str : list)
                {                    
                    if ((counterIn == 0)&&(!str.equalsIgnoreCase(name)))
                    {
                        name = str;                                                 
                    }
                    else if (str.equalsIgnoreCase("man")||str.equalsIgnoreCase("woman"))
                    {
                        gender = str;
                    }
                    else if (str.equalsIgnoreCase("husband")||str.equalsIgnoreCase("wife"))
                    {
                        spouse = list.get(2);
                    }
                    else if (str.equalsIgnoreCase("father")||str.equalsIgnoreCase("mother"))
                    {
                        children.add(list.get(2));                                                                                       
                    }                              
                    counterIn++;
                }                
                counterIn = 0;                                
                counterOut++;     
			}                      
            persons.add(new Person(name, gender, spouse, children));
			br.close();            
		}
        catch (FileNotFoundException e)   
        {  
            System.out.println("The file you have chosen (" + path + ") does not exist!");
            System.out.println("Please insert a viable path!");
            fileLoaded = false;
            e.printStackTrace();  
        }  
        catch (IOException e)   
        {  
            System.out.println("Please insert a viable path!");
            fileLoaded = false;
            e.printStackTrace();  
        }  
		catch(Exception e)
		{
            System.out.println("Please insert a viable path!");
            fileLoaded = false;
			e.printStackTrace();
		}   

        findParents(persons);               
        findSiblings(persons);        
        findUncles(persons);    
        findCousins(persons);  
        findGrandchildren(persons);       
        findGrandparents(persons);  

        return persons;          
    }               

    void findParents(ArrayList<Person> persons)
    {
        for (Person p1 : persons)
        {          
            if (!(p1.getChildren().isEmpty()))     
            {
                for (String childrensName : p1.getChildren())
                {
                    for (Person p2 : persons)
                    {                    
                        if (p2.getName().equals(childrensName))
                        {
                            if (p1.getGender().equals("man"))
                            {
                                p2.setFather(p1.getName());
                            }
                            else
                            {
                                p2.setMother(p1.getName());
                            }                        
                        }
                    }
                }
            }                    
        }        
    }    

    void findSiblings(ArrayList<Person> persons)
    {
        for (Person p1 : persons)
        {
            for (Person p2 : persons)
            {
                if ((!p1.getName().equals(p2.getName())))
                {
                    if (!(p1.getFather().equals(""))&&(!p2.getFather().equals("")))    
                    {
                        if (p1.getFather().equals(p2.getFather()))
                        {
                            p1.getSiblings().add(p2.getName());   
                        }  
                    }      
                    else if (!(p1.getMother().equals(""))&&(!p2.getMother().equals("")))
                    {
                        if (p1.getMother().equals(p2.getMother()))
                        {
                            p1.getSiblings().add(p2.getName());   
                        }  
                    }                         
                }
            }
        }
    }

    void findUncles(ArrayList<Person> persons)
    {
        for (Person p1 : persons)
        {
            for (Person p2 : persons)
            {
                if ((!p1.getName().equals(p2.getName())))
                {
                    if (!(p1.getFather().equals(""))&&(!p2.getFather().equals("")))    
                    {
                        for (String sibling : p2.getSiblings())
                        {
                            if (p1.getFather().equals(sibling))
                            {
                                for (Person p : persons)
                                {
                                    if (p.getName().equals(p2.getName()))
                                    {
                                        if (p.getGender().equals("man"))
                                        {
                                            p1.getUncle().add(p2.getName());
                                            p.getNephews().add(p1.getName());
                                        }
                                        else
                                        {
                                            p1.getAunt().add(p2.getName());
                                            p.getNephews().add(p1.getName());
                                        }
                                    }
                                }
                            }  
                        }                      
                    }      
                    else if (!(p1.getMother().equals(""))&&(!p2.getMother().equals("")))
                    {
                        for (String sibling : p2.getSiblings())
                        {
                            if (p1.getMother().equals(sibling))
                            {
                                for (Person p : persons)
                                {
                                    if (p.getName().equals(p2.getName()))
                                    {
                                        if (p.getGender().equals("man"))
                                        {
                                            p1.getUncle().add(p2.getName());
                                            p.getNephews().add(p1.getName());
                                        }
                                        else
                                        {
                                            p1.getAunt().add(p2.getName());
                                            p.getNephews().add(p1.getName());
                                        }
                                    }
                                }
                            }  
                        }          
                    }                         
                }
            }
        }
    }

    void findCousins(ArrayList<Person> persons)
    {
        for (Person p1 : persons)
        {                        
            for (Person p2 : persons)
            {
                for (String uncle : p1.getUncle())
                {
                    if (uncle.equals(p2.getName()))
                    {
                        for (String child : p2.getChildren())
                        {
                            p1.getCousins().add(child);
                        }
                    }
                }      
                for (String aunt : p1.getAunt())
                {
                    if (aunt.equals(p2.getName()))
                    {
                        for (String child : p2.getChildren())
                        {
                            p1.getCousins().add(child);
                        }
                    }
                }              
            }            
        }
    }

    void findGrandchildren(ArrayList<Person> persons)
    {
        for (Person p1 : persons)
        {
            if (!(p1.getFather().equals("")))
            {
                for (Person p2 : persons)
                {
                    if (p2.getName().equals(p1.getFather()))
                    {
                        if (!(p1.getChildren().isEmpty()))
                        {
                            for (String child : p1.getChildren())
                            {
                                p2.getGrandchildren().add(child);
                            }
                        }
                    }                    
                }
            }       
            if (!(p1.getMother().equals("")))         
            {
                for (Person p2 : persons)
                {
                    if (p2.getName().equals(p1.getMother()))
                    {
                        if (!(p1.getChildren().isEmpty()))
                        {
                            for (String child : p1.getChildren())
                            {
                                p2.getGrandchildren().add(child);
                            }
                        }
                    }                    
                }
            }
        }
    }

    void findGrandparents(ArrayList<Person> persons)
    {
        for (Person p1 : persons)
        {
            if (!(p1.getGrandchildren().isEmpty()))
            {
                for (String gchild : p1.getGrandchildren())
                {
                    for (Person p2 : persons)
                    {
                        if (p2.getName().equals(gchild))
                        {
                            if (p1.getGender().equals("man"))
                            {
                                p2.setGrandfather(p1.getName());
                            }
                            else
                            {
                                p2.setGrandmother(p1.getName());
                            }
                        }
                    }
                }
            }
        }
    }

    ArrayList<Person> sortList(ArrayList<Person> persons)
    {
        ArrayList<Person> list = persons;
        Collections.sort(list, new sortByName());
        return list;
    }

    void writeToFile(ArrayList<Person> persons, String newFilePath)
    {
        try 
        {
            File myObj = new File(newFilePath);
            if (myObj.createNewFile()) 
            {
                System.out.println("File created: " + myObj.getName());
            } 
            else 
            {
                System.out.println("File already exists.");
            }
        } 
        catch (IOException e) 
        {            
            e.printStackTrace();
        }
        catch(Exception e)
		{
			e.printStackTrace();
		} 

        try 
        {
            FileWriter myWriter = new FileWriter(newFilePath);
            
            for (Person person : sortList(persons))
            {
                myWriter.write(person.getName() + " " + person.getGender());
                myWriter.write(System.getProperty( "line.separator" ));
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } 
        catch (IOException e) 
        {            
            e.printStackTrace();
        }
        catch(Exception e)
		{
			e.printStackTrace();
		} 
    }  
}

class sortByName implements Comparator<Person> 
{        
    public int compare(Person p1, Person p2)
    { 
        return p1.getName().compareTo(p2.getName());
    }
}

class Person 
{         
    private String name;
    private String gender;
    private String spouse;
    private ArrayList<String> children; 
    private String father;   
    private String mother;   
    private String grandfather;   
    private String grandmother;   
    private ArrayList<String> uncle;   
    private ArrayList<String> aunt;   
    private ArrayList<String> siblings;    
    private ArrayList<String> nephews;
    private ArrayList<String> cousins;
    private ArrayList<String> grandchildren;

    Person(String name, String gender, String spouse, ArrayList<String> children) 
    {
        this.name = name;
        this.gender = gender;
        this.spouse = spouse;
        this.children = children;  
        father = "";   
        mother = "";   
        grandfather = "";   
        grandmother = "";   
        uncle = new ArrayList<>(); 
        aunt = new ArrayList<>();  
        siblings = new ArrayList<>();
        nephews = new ArrayList<>();
        cousins = new ArrayList<>();             
        grandchildren = new ArrayList<>();             
    }
    public String getName() 
    {
        return name;
    }
    public void setName(String name) 
    {
        this.name = name;
    }
    public String getGender() 
    {
        return gender;
    }
    public void setGender(String gender) 
    {
        this.gender = gender;
    }
    public String getSpouse() 
    {
        return spouse;
    }
    public void setSpouse(String spouse) 
    {
        this.spouse = spouse;
    }
    public ArrayList<String> getChildren() 
    {
        return children;
    }
    public void setChildren(ArrayList<String> children) 
    {
        this.children = children;
    }
    public String getFather() 
    {
        return father;
    }
    public void setFather(String father) 
    {
        this.father = father;
    }
    public String getMother() 
    {
        return mother;
    }
    public void setMother(String mother) 
    {
        this.mother = mother;
    }
    public String getGrandfather() 
    {
        return grandfather;
    }
    public void setGrandfather(String grandfather) 
    {
        this.grandfather = grandfather;
    }
    public String getGrandmother() 
    {
        return grandmother;
    }
    public void setGrandmother(String grandmother) 
    {
        this.grandmother = grandmother;
    }    
    public ArrayList<String> getNephews() 
    {
        return nephews;
    }
    public void setNephews(ArrayList<String> nephews) 
    {
        this.nephews = nephews;
    }
    public ArrayList<String> getCousins() 
    {
        return cousins;
    }
    public void setCousins(ArrayList<String> cousins) 
    {
        this.cousins = cousins;
    }    
    public ArrayList<String> getSiblings() 
    {
        return siblings;
    }
    public void setSiblings(ArrayList<String> siblings) 
    {
        this.siblings = siblings;
    }
    public ArrayList<String> getUncle() 
    {
        return uncle;
    }
    public void setUncle(ArrayList<String> uncle) 
    {
        this.uncle = uncle;
    }
    public ArrayList<String> getAunt() 
    {
        return aunt;
    }
    public void setAunt(ArrayList<String> aunt) 
    {
        this.aunt = aunt;
    }
    public ArrayList<String> getGrandchildren() 
    {
        return grandchildren;
    }
    public void setGrandchildren(ArrayList<String> grandchildren) 
    {
        this.grandchildren = grandchildren;
    }
    @Override
    public String toString() 
    {
        return "Person [name=" + name + ", gender=" + gender + ", spouse=" + spouse + ", children=" + children
                + ", father=" + father + ", mother=" + mother + ", grandfather=" + grandfather + ", grandmother="
                + grandmother + ", uncle=" + uncle + ", aunt=" + aunt + ", siblings=" + siblings + ", nephews="
                + nephews + ", cousins=" + cousins + ", grandchildren=" + grandchildren + "]";
    }
}

