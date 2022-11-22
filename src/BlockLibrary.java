public class BlockLibrary {
    
    int x[];
    int y[];
    int width[];
    int height[];
    Block savedBlocks[];

    public BlockLibrary(Block blocks[]) {

        int arrayLength = blocks.length;

        x = new int[arrayLength];
        y = new int[arrayLength];
        width = new int[arrayLength];
        height = new int[arrayLength];
        savedBlocks = new Block[arrayLength];

        for(int i = 0; i < arrayLength; i++){
            savedBlocks[i] = new Block(blocks[i]);
        }
        //Sort blocks by their height
        int h;

        for(h = 1; h <= savedBlocks.length / 9; h = h * 3 + 1);

        while (h >= 1) {
            for (int i = h; i < savedBlocks.length; i++)
                for(int j = i - h; j >= 0 && savedBlocks[j].GetUpY() > savedBlocks[j+h].GetUpY(); j-=h){
                    Block temp = new Block(savedBlocks[j]);
                
                    savedBlocks[j].SetData(savedBlocks[j + h]);
                    savedBlocks[j+h].SetData(temp);
                }
            
            h = (h - 1) / 3;
        }
    }

    public Block[] GetResult() {
        return savedBlocks;
    }

    public void ChangeWidth(int newWidth)
    {

        // Find to divide

        int maxX = 0;
        int arrayLength = savedBlocks.length;

        for (int i = 0; i < arrayLength; i++) {
            if (savedBlocks[i].GetRightX() > maxX)
                maxX = savedBlocks[i].GetRightX();
        }

        double divider = (float)newWidth / (float)maxX;

        System.out.println(divider);

        for (int i = 0; i < arrayLength; i++) {
          //  if (savedBlocks[i].GetRightX() > maxX)
          //      maxX = savedBlocks[i].GetRightX();

            savedBlocks[i].SetData((int)(savedBlocks[i].GetLeftX()*divider), (int)(savedBlocks[i].GetRightX()*divider), 
                    (int)(savedBlocks[i].GetUpY() * divider), (int)(savedBlocks[i].GetDownY() * divider), savedBlocks[i].GetText());

            int yDif = savedBlocks[i].GetDownY() - savedBlocks[i].GetUpY();
            int xDif = savedBlocks[i].GetRightX() - savedBlocks[i].GetLeftX();

            int addY = 0;
            while (xDif * (yDif+addY) < 50){
                addY++;
            }

            savedBlocks[i].SetData((int) (savedBlocks[i].GetLeftX()),
                    (int) (savedBlocks[i].GetRightX()),
                    (int) (savedBlocks[i].GetUpY()), 
                    (int) (savedBlocks[i].GetDownY()+addY),
                    savedBlocks[i].GetText());
        }
    }

    public static void main(String[] args) {

        Block block1 = new Block(8, 16, 0, 18, "Текст 1 Текст 1");
        Block block2 = new Block(129, 200, 5, 18, "Текст 2 Текст 2");
        Block block3 = new Block(251, 300, 101, 200, "Текст 3 Текст 3");

        Block testArray[];
        testArray = new Block[3];
        testArray[0] = new Block(block1);
        testArray[1] = new Block(block2);
        testArray[2] = new Block(block3);

        BlockLibrary bl = new BlockLibrary(testArray);
        bl.ChangeWidth(150);

        Block resultArray[];
        resultArray = bl.GetResult();

        for(int i = 0; i < resultArray.length; i++)
        {
            System.out.println("("+resultArray[i].GetLeftX()+", "+ resultArray[i].GetRightX()+", "+ resultArray[i]
                    .GetUpY()+", "+ resultArray[i].GetDownY()+", |"+ resultArray[i].GetText()+"|)");
        }
    }


}
