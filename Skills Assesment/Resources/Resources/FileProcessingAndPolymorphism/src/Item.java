/**
 * Item class extends MyWidget
 * @author srao3
 */
public class Item extends MyWidget{
    private String itemName;
    private float itemCost;
    private float profitMargin;

    public Item()
    {
        itemName="noname";
        itemCost=0f;
        profitMargin = 0f;        
    }
    public Item(String itemName) {
        this.itemName = itemName;
        this.itemCost = 0f;
        this.profitMargin = 0f;
    }

    public Item(String itemName, float itemCost) {
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.profitMargin = 0f;
    }

    public Item(String itemName, float itemCost, float profitMargin){
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.profitMargin = profitMargin;
    }

    public void SetData(String itemName){
        this.itemName = itemName;
    }

    public void SetData(String itemName, float itemCost){
        this.itemName = itemName;
        this.itemCost = itemCost;
    }

    public void SetData(String itemName, float itemCost, float profitMargin){
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.profitMargin = profitMargin;
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

    public String getItemCostAsString(){

        String out = "";
        try{
            out = Float.toString(itemCost);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
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

    public void setItemCost(float itemCost) {
        this.itemCost = itemCost;
    }

    public float getProfitMargin() {
        return profitMargin;
    }

    //default value for items
    @Override
    public void setProfitMargin() {
        this.profitMargin = 0.25f;
    }

    public void setProfitMargin(float margin){
        this.profitMargin = margin;
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
    
}
