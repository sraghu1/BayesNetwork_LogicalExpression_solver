import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;


public class Task2 {
public static void main(String args[])
{
	Task2 t2=new Task2();
	try{
	BufferedReader br=new BufferedReader(new FileReader(new File(args[0])));
	ArrayList<Integer> baseball=new ArrayList<Integer>();
	ArrayList<Integer> tv=new ArrayList<Integer>();
	ArrayList<Integer> catFood=new ArrayList<Integer>();
	ArrayList<Integer> catFeed=new ArrayList<Integer>();
	String line="";
	while((line=br.readLine())!=null)
	{
		String parts[]=line.split("     ");
		baseball.add(Integer.parseInt(parts[0]));
		tv.add(Integer.parseInt(parts[1]));
		catFood.add(Integer.parseInt(parts[2]));
		catFeed.add(Integer.parseInt(parts[3]));
	}
	System.out.println("P(Baseball on TV): "+t2.calculateProbability(baseball));
	System.out.println("P(Out of Cat food): "+t2.calculateProbability(catFood));
	System.out.println("P(George Watches TV/baseball on TV): "+t2.calculateConditional(tv, baseball,null));
	System.out.println("P(George feed the cat/George watches TV,Out of Catfood): "+t2.calculateConditional(catFeed, tv, catFood));
	}
	catch(Exception e)
	{
		System.out.println("File not found/ Path incorrect");
	}
}
public double calculateProbability(ArrayList<Integer> variable)
{
	double ret=0.0;
	int trueProbabilities=0;
	int i=0;
	for(int a:variable)
	{
		if(a!=0)
		{
			trueProbabilities+=a;
		}
		i++;
	}
	ret=(double)trueProbabilities/(double)i;
	return ret;
}
public double calculateConditional(ArrayList<Integer> a, ArrayList<Integer> b, ArrayList<Integer> c)
{
	//p(a/b)=p(a=true)*p(b=true)/p(b=true)
	
	double ret=0.0;
	int numerator=0;
	int denominator=0;
	if(c==null){
	for(int i=0;i<a.size();i++)
	{
		if(b.get(i)==1)
		{
			denominator++;
			if(a.get(i)==1)
			{
				numerator++;
			}
		}
	}
	}
	else
	{
		for(int i=0;i<a.size();i++)
		{
			if(b.get(i)==1&&c.get(i)==1)
			{
				denominator++;
				if(a.get(i)==1)
				{
					numerator++;
				}
				
			}
		}
	}
	ret=(double)numerator/(double)denominator;
	
	return ret;
}
}
