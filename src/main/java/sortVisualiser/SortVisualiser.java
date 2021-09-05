package sortVisualiser;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;

import sortVisualiser.algorithms.*;

import static util.Sleep.secondsToNano;
import static util.Sleep.sleepFor;

/**
 * The main class for the sort visualiser GUI
 * @author Matt Hopson
 */
public class SortVisualiser {
    private final JFrame window;
    private final SortArray sortArray;
    private final ArrayList<ISortAlgorithm> sortQueue;
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Creates the GUI
     */
    public SortVisualiser() {
        window = new JFrame("Sort Visualiser");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        sortArray = new SortArray();
        window.add(sortArray);
        window.pack();
        window.setVisible(true);

        sortQueue = new ArrayList<>();
    }

    private void shuffleAndWait() {
        sortArray.shuffle();
        sortArray.resetColours();
        sleepFor(secondsToNano(2));
    }

    public void run() {
        //*************************************************************

        System.out.println("-----------------------------------------------------");
        System.out.println(" Hi! Choose the procedures you would like to sort ");
        System.out.println("-----------------------------------------------------");
        boolean quit = false;
        int choice = 0;
        printInstructions();
        while (!quit) {
            System.out.print("\nEnter your choice: ");
            if(!scanner.hasNextInt()){
                scanner.nextLine();
                System.out.println("You can only press number 0-7\n");
            }else {
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 0:
                        printInstructions();
                        break;
                    case 1:
                        sortQueue.add(new MergeSort());
                        System.out.println("=== === === === Merge sort is successfully added in order: " + sortQueue.size() +"!");
                        break;
                    case 2:
                        sortQueue.add(new SelectionSort());
                        System.out.println("=== === === === Selection sort is successfully added in order: " + sortQueue.size() +"!");
                        break;
                    case 3:
                        sortQueue.add(new InsertionSort());
                        System.out.println("=== === === === Insertion sort is successfully added in order: " + sortQueue.size() +"!");
                        break;
                    case 4:
                        sortQueue.add(new BubbleSort());
                        System.out.println("=== === === === Bubble sort is successfully added in order: " + sortQueue.size() +"!");
                        break;
                    case 5:
                        printQueue();
                        System.out.print("Enter array index that you want to remove: ");
                        int index = scanner.nextInt();
                        RemoveElement(index);
                        break;
                    case 6:
                        printQueue();
                        visualize();
                        break;
                    case 7:
                        quit = true;
                        break;
                    default:
                        System.out.println("Please enter only number 0-6\n");
                        break;
                }
            }
        }

        //*************************************************************

    }

    public static void printInstructions() {
        System.out.println("\nPress ");
        System.out.println("-------------------------------------------------------------------");
        System.out.println("\t === 0 - To print choice options. ");
        System.out.println("\t === 1 - To add Merge sort algorithm into the waiting list");
        System.out.println("\t === 2 - To add Selection sort algorithm into the waiting list");
        System.out.println("\t === 3 - To add Insertion sort algorithm into the waiting list");
        System.out.println("\t === 4 - To add Bubble sort algorithm into the waiting list");
        System.out.println("\t === 5 - To remove the element from the list by the given index");
        System.out.println("\t === 6 - To visualize algorithms which you added respectively");
        System.out.println("\t === 7 - To end the program");
        System.out.println("-------------------------------------------------------------------");
    }

    public void RemoveElement(int index){
        if(index >= 0 && index < sortQueue.size()){
            System.out.println(sortQueue.remove(index) + "is successfully removed");
        }
        System.out.println("Your input is invalid!, please input only number 0-"+sortQueue.size());
    }

    public void visualize(){
        for (ISortAlgorithm algorithm : sortQueue) {
            sleepFor(secondsToNano(1));
            shuffleAndWait();
            algorithm.runSort(sortArray);
            System.out.println(algorithm.getName() + " DONE!");
            sortArray.resetColours();
            sortArray.highlightArray();
            sortArray.resetColours();
        }
    }

    public void printQueue(){
        System.out.println("=== === === Sorting Queue === === ===");
        for(int i = 0; i < sortQueue.size(); i++){
            System.out.println("index: "+ i + ", "+ sortQueue.get(i).getName());
        }
    }

    public static void main(String... args) {
        SortVisualiser sortVisualiser = new SortVisualiser();
        sortVisualiser.run();
    }
}
