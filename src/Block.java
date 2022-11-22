public class Block {
    private int leftX = 0;
    private int rightX = 0;
    private int upY = 0;
    private int downY = 0;

    private String text = "";

    public Block(int newLeftX, int newRightX, int newUpY, int newDownY, String newText) {

        leftX = newLeftX;
        rightX = newRightX;
        upY = newUpY;
        downY = newDownY;

        text = newText;

    }

    public Block(Block newBlock)
    {
        leftX = newBlock.GetLeftX();
        rightX = newBlock.GetRightX();
        upY = newBlock.GetUpY();
        downY = newBlock.GetDownY();

        text = newBlock.GetText();
    }

    public void SetData(Block newBlock) 
    {
        leftX = newBlock.GetLeftX();
        rightX = newBlock.GetRightX();
        upY = newBlock.GetUpY();
        downY = newBlock.GetDownY();

        text = newBlock.GetText();
    }

    public void SetData(int newLeftX, int newRightX, int newUpY, int newDownY, String newText) {

        leftX = newLeftX;
        rightX = newRightX;
        upY = newUpY;
        downY = newDownY;

        text = newText;

    }


    public Block(){

    }

    public int GetLeftX(){
        return leftX;
    }

    public int GetRightX() {
        return rightX;
    }

    public int GetUpY() {
        return upY;
    }

    public int GetDownY() {
        return downY;
    }
    
    public String GetText() {
        return text;
    }
}
