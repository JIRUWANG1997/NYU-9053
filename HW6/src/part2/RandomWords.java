package part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import javafx.util.Pair;

public class RandomWords {
	private List<String> sourceWords;
	private String filename;
	/* choose ArrayList<String> or String[] to store the words */

	public RandomWords() throws IOException {
		try (BufferedReader reader = Files.newBufferedReader(Paths.get("./data/words"))) {
			this.sourceWords = reader.lines().collect(Collectors.toList());    // YOUR CODE HERE
			System.out.println("Loaded " + sourceWords.size() + " words");
		}
	}

	public List<String> createList(int listSize) {
		Random rand = new Random();
		int index = (int) (Math.random()* 100);
		List<String> wordList = rand


				// YOUR CODE HERE
				.ints(listSize, 0, this.sourceWords.size())
				.mapToObj(i -> this.sourceWords.get(i))
				.collect(Collectors.toList());
		return wordList;
	}
	public List<Pair<String, String>> getPairs(){
		List<String> wordList = createList(10);
		List<Pair<String, String>> list = new ArrayList<Pair<String, String>>();
		for(int i=0;i< wordList.size()-1;i++)
		{
			for(int j=i+1;j< wordList.size();j++)
			{
				Pair<String,String> wordPair = new Pair<String, String>(wordList.get(i), wordList.get(j));

				list.add(wordPair);
			}
		}

		return list;
	}

	public static int getStringDistance(String s1, String s2) {

		double distance[][];
		int s1_len = s1.length();
		int s2_len = s2.length();

		if (s1_len == 0) {
			return s2_len;
		}
		if (s2_len == 0) {
			return s1_len;
		}
		distance = new double[s1_len + 1][s2_len + 1];


		for (int i = 0; i <= s1_len; i++) {
			distance[i][0] = i;
		}
		for (int j = 0; j <= s2_len; j++) {
			distance[0][j] = j;
		}

		for (int i = 1; i <= s1_len; i++) {
			char s1_i = s1.charAt(i - 1);
			for (int j = 1; j <= s2_len; j++) {
				char s2_j = s2.charAt(j - 1);

				if (s1_i == s2_j) {
					distance[i][j] = distance[i - 1][j - 1];
				} else {

					distance[i][j] = getMin(distance[i - 1][j], distance[i][j - 1], distance[i - 1][j - 1]) + 1;
				}
			}
		}
		return (int)distance[s1_len][s2_len];
	}

	private static double getMin(double a, double b, double c) {
		double min = a;
		if (b < min) {
			min = b;
		}
		if (c < min) {
			min = c;
		}
		return min;
	}


	public static int levenshteinDistance(String word1, String word2) {

		return getStringDistance(word1,word2);
	}

	public class list_c implements Comparator<Pair<String,String>>{

		@Override
		public int compare(Pair<String, String> p1, Pair<String, String> p2) {
			int a = RandomWords.levenshteinDistance(p1.getKey(),p1.getValue());
			int b = RandomWords.levenshteinDistance(p2.getKey(),p2.getValue());
			if (a > b){
				return -1;
			}else if(a < b){
				return 1;
			}else{
				return 0;
			}
		}
	}


	public List<Pair<String, String>> sortedPairsComparator() {
		List<Pair<String, String>> wp = this.getPairs();


		wp.sort(new list_c());
		return wp;
	}
	
	public List<Pair<String, String>> sortedPairsLambda() {
		List<Pair<String, String>> wp = this.getPairs();

		wp.sort((p1,p2) -> -RandomWords.levenshteinDistance(p1.getKey(),p1.getValue())
		+RandomWords.levenshteinDistance(p2.getKey(),p2.getValue()));
		return wp;
	}
	
	public static void main(String[] args) throws IOException {
		RandomWords r = new RandomWords();
		List<Pair<String, String>> s = r.getPairs();
		List<Pair<String, String>> l = r.sortedPairsLambda();
		List<Pair<String, String>> l2 = r.sortedPairsComparator();



		
	}
}
