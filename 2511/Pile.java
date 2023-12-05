import java.util.Random;

public class Pile {
  private int numNims;

  public Pile() {
    this.numNims = generateRandomNims();
  }

  private int generateRandomNims() {
    Random rand = new Random();
    return rand.nextInt(31) + 20; // Generates a random number between 20 and 50
  }

  public int getNumNims() {
    return numNims;
  }

  public void removeNims(int n) {
    numNims -= n;
  }

}
