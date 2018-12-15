public class CollisionLogger {
    /* add data members here */
    private int screenWidth;
    private int screenHeight;
    private int bucketWidth;
    private int[][] hit;
    
    public CollisionLogger(int screenWidth, int screenHeight, int bucketWidth) {
    	/* constructor code here */
    	this.screenWidth = screenWidth;
    	this.screenHeight = screenHeight;
    	this.bucketWidth = bucketWidth;
    	hit = new int[screenHeight/bucketWidth][screenWidth/bucketWidth];
    }

     /**
     * This method records the collision event between two balls. Specifically, in increments the bucket corresponding to
     * their x and y positions to reflect that a collision occurred in that bucket.
     */
    public void collide(Ball one, Ball two) {
    	/* update data members to reflect the collision event between ball "one" and ball "two" */
    	int xBucket = (int) (one.getXPos()+two.getXPos())/(2*bucketWidth);
    	int yBucket = (int) (two.getYPos()+one.getYPos())/(2*bucketWidth);
    	if(yBucket < 0){
    	    yBucket = 0;
        }else if(yBucket > hit.length-1){
    	    yBucket = hit.length -1;
        }
    	if(xBucket < 0){
    	    xBucket = 0;
        }else if(xBucket > hit[yBucket].length -1){
    	    xBucket = hit[yBucket].length -1;
        }
        hit[yBucket][xBucket] += 1;
    }

    /**
     * Returns the heat map scaled such that the bucket with the largest number of collisions has value 255,
     * and buckets without any collisions have value 0.
     */
    public int[][] getNormalizedHeatMap() {
    	
        int[][] normalizedHeatMap = new int[hit.length][hit[0].length]; //NOTE-- these dimensions need to be updated properly!!
    	/* implement your code to produce a normalized heat map of type int[][] here */
    	//find max
        int max = 0;
        for(int i = 0; i < hit.length; i++){
            for(int j = 0; j < hit[i].length; j++){
                if(max < hit[i][j]){
                    max = hit[i][j];
                }
            }
        }
        if(max == 0){
            max = 1;
        }
         for(int i = 0; i < normalizedHeatMap.length; i++){
            for(int j = 0; j < normalizedHeatMap[i].length; j++){
                normalizedHeatMap[i][j] = (hit[i][j]*255)/max;
            }
         }

        return normalizedHeatMap;
    }
}
