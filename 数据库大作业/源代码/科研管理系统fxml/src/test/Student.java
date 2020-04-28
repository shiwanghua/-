package test;


public class Student {

    private String Name;

    private String Number;

    private String Class;

    private int Score;

    

    

    public Student(){

        this("sdiuf h","6544534","ef esf",48);

    }

            

    public Student(String Name,String Number,String Class){

        this(Name,Number,Class,0);

    }

    

    public Student(String Name,String Class,String Number,int Score){

        this.Name = Name;

        this.Number = Number;

        this.Score = Score;

        this.Class = Class;

    }

    

    public void SetName(String Name){

        this.Name = Name;

    }

    

    public void SetNumber(String Number){

        this.Number = Number;

    }

    

    public void SetClass(String Class){

        this.Class = Class;

    }

    

    public void SetScore(int Score){

        this.Score = Score;

    }

    

    public String GetName(){

        return Name;

    }

    

    public String GetNumber(){

        return Number;

    }

    

    public String GetClass(){

        return Class;

    }

    

    public int GetScore(){

        return Score;

    } 

}
