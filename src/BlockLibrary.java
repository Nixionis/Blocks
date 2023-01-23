public class BlockLibrary {
    
    Block savedBlocks[];

    public BlockLibrary(Block blocks[]) {

        int arrayLength = blocks.length;
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

            int xLeftToPlace = savedBlocks[i].GetLeftX();
            int xRightToPlace = savedBlocks[i].GetRightX();
            int yUpToPlace = savedBlocks[i].GetUpY();
            int yDownToPlace = savedBlocks[i].GetDownY();

            if (xLeftToPlace > xRightToPlace)
                {
                    int temp = xLeftToPlace;
                    xLeftToPlace = xRightToPlace;
                    xRightToPlace = temp;
                }

            if (yUpToPlace > yDownToPlace) {
                int temp = yDownToPlace;
                yDownToPlace = yUpToPlace;
                yUpToPlace = temp;
            }

            savedBlocks[i].SetData((int)(xLeftToPlace*divider), (int)(xRightToPlace*divider), 
                    (int)(yUpToPlace * divider), (int)(yDownToPlace * divider), savedBlocks[i].GetText());

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

    public static void main(String[] args)  {

        Block block1 = new Block(1531, 2713, 2911, 2915, "1");
        Block block2 = new Block(1840, 1873, 2800, 2953, "2");
        Block block3 = new Block(719, 950, 2963, 2966, "3");
        Block block4 = new Block(998, 2342, 1804, 2923, "4");
        Block block5 = new Block(1025, 2555, 1582, 2783, "5");

        Block testArray[];
        testArray = new Block[5];
        testArray[0] = new Block(block1);
        testArray[1] = new Block(block2);
        testArray[2] = new Block(block3);
        testArray[3] = new Block(block4);
        testArray[4] = new Block(block5);

        BlockLibrary bl = new BlockLibrary(testArray);
        bl.ChangeWidth(1809);

        Block resultArray[];
        resultArray = bl.GetResult();

        for(int i = 0; i < resultArray.length; i++)
        {
            System.out.println("("+resultArray[i].GetLeftX()+", "+ resultArray[i].GetRightX()+", "+ resultArray[i]
                    .GetUpY()+", "+ resultArray[i].GetDownY()+", |"+ resultArray[i].GetText()+"|)");
        }
    }


}
