package examples.ch14;

/**
 * This class contains the data model for the PlayerTable
 */
public class PlayerTableModel {
  public Team[] teams;

  /**
   * Constructs a PlayerTableModel Fills the model with data
   */
  public PlayerTableModel() {
    teams = new Team[3];

    teams[0] = new Team("Celtics", "1985-86");
    teams[0].add(new Player("Larry", "Bird", 25.8f, 9.8f, 6.8f));
    teams[0].add(new Player("Kevin", "McHale", 21.3f, 8.1f, 2.7f));
    teams[0].add(new Player("Robert", "Parish", 16.1f, 9.5f, 1.8f));
    teams[0].add(new Player("Dennis", "Johnson", 15.6f, 3.4f, 5.8f));
    teams[0].add(new Player("Danny", "Ainge", 10.7f, 2.9f, 5.1f));
    teams[0].add(new Player("Scott", "Wedman", 8.0f, 2.4f, 1.1f));
    teams[0].add(new Player("Bill", "Walton", 7.6f, 6.8f, 2.1f));
    teams[0].add(new Player("Jerry", "Sichting", 6.5f, 1.3f, 2.3f));
    teams[0].add(new Player("David", "Thirdkill", 3.3f, 1.4f, 0.3f));
    teams[0].add(new Player("Sam", "Vincent", 3.2f, 0.8f, 1.2f));
    teams[0].add(new Player("Sly", "Williams", 2.8f, 2.5f, 0.3f));
    teams[0].add(new Player("Rick", "Carlisle", 2.6f, 1.0f, 1.4f));
    teams[0].add(new Player("Greg", "Kite", 1.3f, 2.0f, 1.3f));

    teams[1] = new Team("Bulls", "1995-96");
    teams[1].add(new Player("Michael", "Jordan", 30.4f, 6.6f, 4.3f));
    teams[1].add(new Player("Scottie", "Pippen", 19.4f, 6.4f, 5.9f));
    teams[1].add(new Player("Toni", "Kukoc", 13.1f, 4.0f, 3.5f));
    teams[1].add(new Player("Luc", "Longley", 9.1f, 5.1f, 1.9f));
    teams[1].add(new Player("Steve", "Kerr", 8.4f, 1.3f, 2.3f));
    teams[1].add(new Player("Ron", "Harper", 7.4f, 2.7f, 2.6f));
    teams[1].add(new Player("Dennis", "Rodman", 5.5f, 14.9f, 2.5f));
    teams[1].add(new Player("Bill", "Wennington", 5.3f, 2.5f, 0.6f));
    teams[1].add(new Player("Jack", "Haley", 5.0f, 2.0f, 0.0f));
    teams[1].add(new Player("John", "Salley", 4.4f, 3.3f, 1.3f));
    teams[1].add(new Player("Jud", "Buechler", 3.8f, 1.5f, 0.8f));
    teams[1].add(new Player("Dickey", "Simpkins", 3.6f, 2.6f, 0.6f));
    teams[1].add(new Player("James", "Edwards", 3.5f, 1.4f, 0.4f));
    teams[1].add(new Player("Jason", "Caffey", 3.2f, 1.9f, 0.4f));
    teams[1].add(new Player("Randy", "Brown", 2.7f, 1.0f, 1.1f));

    teams[2] = new Team("Lakers", "1987-1988");
    teams[2].add(new Player("Magic", "Johnson", 23.9f, 6.3f, 12.2f));
    teams[2].add(new Player("James", "Worthy", 19.4f, 5.7f, 2.8f));
    teams[2].add(new Player("Kareem", "Abdul-Jabbar", 17.5f, 6.7f, 2.6f));
    teams[2].add(new Player("Byron", "Scott", 17.0f, 3.5f, 3.4f));
    teams[2].add(new Player("A.C.", "Green", 10.8f, 7.8f, 1.1f));
    teams[2].add(new Player("Michael", "Cooper", 10.5f, 3.1f, 4.5f));
    teams[2].add(new Player("Mychal", "Thompson", 10.1f, 4.1f, 0.8f));
    teams[2].add(new Player("Kurt", "Rambis", 5.7f, 5.8f, 0.8f));
    teams[2].add(new Player("Billy", "Thompson", 5.6f, 2.9f, 1.0f));
    teams[2].add(new Player("Adrian", "Branch", 4.3f, 1.7f, 0.5f));
    teams[2].add(new Player("Wes", "Matthews", 4.2f, 0.9f, 2.0f));
    teams[2].add(new Player("Frank", "Brickowski", 4.0f, 2.6f, 0.3f));
    teams[2].add(new Player("Mike", "Smrek", 2.2f, 1.1f, 0.1f));
  }
}