package com.example.hangmangame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;
import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
public class HangmanApplication {
	
	private static final String[] word = {"stackoverflow", "recursion", "palindrome", "hashmap"};
	private static final String selectedWord = word[(int) ((Math.random() * (word.length - 0)) + 0)];
	private static char guessLetter;
	private static HashSet<Character> guessedLetters = new HashSet<>();

	public static void main(String[] args) {
		SpringApplication.run(HangmanApplication.class, args);
		
		System.out.println("Type a letter to start your hangman game!");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		int selectedWordLength = selectedWord.length();
		int count = 0;
		int strike = 0;
		char[] display = new char[selectedWordLength];
		Arrays.fill(display, '-');
		
		while (count != selectedWordLength) {
			guessLetter = input.next().charAt(0);
			if (guessedLetters.contains(guessLetter)) {
				System.out.println("You've already guessed that letter.");
			}  else if (selectedWord.indexOf(guessLetter) == -1) {
				strike++;
				if (strike == 3) {
					System.out.println("Sorry, you lose T_T");
					System.exit(0);
				}
				System.out.println("Nope, letter not in word :(");
			} else {
				guessedLetters.add(guessLetter);
				for (int i = 0; i < selectedWordLength; i++) {
					if (selectedWord.charAt(i) == guessLetter) {
						count++;
						display[i] = selectedWord.charAt(i);
					}
				}
				System.out.println(new String(display));
			}
		}
		if (selectedWordLength == count) {
			System.out.println("You win!");
			System.exit(0);
		}
	}

}
