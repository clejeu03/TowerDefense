package View;

/**
 * Project - TowerDefense</br>
 * <b>Class - MainMenusView</b></br>
 * <p>The MainMenusView class is an abstract class that contains main caracteristics needes 
 * by the differents Menus to display properly. The MainMenusView inherit from JFrame to display
 * a Menu that take all the surface of the screen. 
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see EndMenu
 * @see OptionMenu
 * @see AccueilMenu
 * @see ViewManager
 * 
 */
public abstract class MainMenusView extends JFrame {
  /**
   * Contains the x size of the Menu
   */
  public int size_x;
  /**
   * Contains the y size of the Menu
   */
  public int size_y;

  /**
   * Constructor of the MainMenusView class
   */
  public MainMenusView() {
  }
  /**
   * Setter that can changes the size of the selected menu
   * @param size - the new size to apply
   */
  public void setSize() {
  }
  /**
   * Getter thet return the current size of the selected Menu
   * @return size
   */
  public void getSize() {
  }

}