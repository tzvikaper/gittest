package hello;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Scanner;
import java.io.File;
import java.util.*;
import java.util.Arrays;
import java.util.concurrent.*;

@RestController
public class SimilarController {
    private HashMap<String,ArrayList<String>> dictionary = new HashMap<String,ArrayList<String>>();
    private int totalWords = 0;
    private int totalRequests = 0;
    private long sumOfProcessingTimesNs = 0;
//Algorithm - store in a hash table a list of words that have the same sorted form
    public SimilarController() throws java.io.FileNotFoundException
    {
	Scanner s = new Scanner(new File("words_clean.txt"));
	while(s.hasNextLine())
	{
	     String word = s.nextLine();
	     String orderedWord = OrderWord(word);
	     if(dictionary.containsKey(orderedWord))
	     {
		  dictionary.get(orderedWord).add(word);
             }
	     else
	     {
		ArrayList<String> list = new ArrayList<String>();
		list.add(word);
		dictionary.put(orderedWord,list);
 	     }
	     totalWords++;
	}
    }

    @RequestMapping("/api/v1/similar")
    public Similar similar(@RequestParam(value="word", defaultValue="stressed") String name) {
	long statTime = System.nanoTime();
        String orderedWord = OrderWord(name);
	Similar result;

	if(dictionary.containsKey(orderedWord))
	{
		ArrayList<String> list = (ArrayList<String>) ((dictionary.get(orderedWord)).clone()); 
		list.remove(name); //remove the input word
		String[] arr = new String[0];
		result = new Similar(list.toArray(arr));
	}
	else
	{
		String[] arr = new String[0];
		result = new Similar(arr);
	}
	long endTime = System.nanoTime();
        IncreamentRequestsCount(endTime-statTime);
	return result;
    }

    @RequestMapping("/api/v1/stats")
    public Status stats() {
	Status result;
	if(totalRequests!=0)
	{
		result= new Status(totalWords,totalRequests,(int)sumOfProcessingTimesNs/totalRequests);
	}
	else
	{
		result= new Status(totalWords,0,0);
	}
        return result;
    }

    private synchronized void IncreamentRequestsCount(long timeNs)
    {
	totalRequests++;
	sumOfProcessingTimesNs+=timeNs;
    }

    private String OrderWord(String word)
    {
	char[] ch = word.toCharArray();
	Arrays.sort(ch);
	return new String(ch);
    }
}
