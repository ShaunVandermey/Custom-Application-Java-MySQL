import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Item class extends MyWidget
 * @author srao3
 */
public class SerializeItem implements Serializable{
    private String itemName;
    private float itemCost;
    private float profitMargin;

    public SerializeItem()
    {
        itemCost=0f;
        itemName="noname";
        profitMargin = 0f;        
    }
    public SerializeItem(String itemName) {
        this.itemName = itemName;
    }

    public SerializeItem(String itemName, float itemCost) {
        this.itemName = itemName;
        this.itemCost = itemCost;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public float getItemCost() {
        return itemCost;
    }

    public String getItemCostAsString() {
        String out = "";
        try{
            out = Float.toString(itemCost);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    public void setItemCost(float itemCost) {
        this.itemCost = itemCost;
    }

    public float getProfitMargin() {
        return profitMargin;
    }

    public void setProfitMargin() {
        this.profitMargin = 0.25f;
    }

    public void setProfitMargin(float margin){
        this.profitMargin = margin;
    }

    public String getItemMarginAsString(){
        String out = "";
        try{
            out = Float.toString(profitMargin);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }
    
    @Override
    public String toString()
    {
        String temp = "";
        temp = this.itemName+";";
        temp += String.valueOf(itemCost)+";";
        temp += String.valueOf(profitMargin);
        
        return temp;
    }

    private void writeObject(ObjectOutputStream oos)
            throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(this);
    }

    private void readObject(ObjectInputStream ois)
            throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        SerializeItem temp = (SerializeItem) ois.readObject();
        this.setItemName(temp.getItemName());
        this.setItemCost(temp.getItemCost());
        this.setProfitMargin(temp.getProfitMargin());
    }
    
}
