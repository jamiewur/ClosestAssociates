/**
 * Class implement a pair object.
 * Note javafx is not available on the core teaching servers hence we are
 * developing our own.
 *
 * @author Jeffrey Chan, 2019
 */
class MyPair implements Comparable<MyPair> {

    private String mVert;
    private Integer mWeight;

    public MyPair(String vert, Integer weight) {
        mVert = vert;
        mWeight = weight;
    }

    public String getKey() {
        return mVert;
    }

    public void setKey(String mVert) {
        this.mVert = mVert;
    }

    public Integer getValue() {
        return mWeight;
    }

    public void setValue(Integer mWeight) {
        this.mWeight = mWeight;
    }

    @Override
    public int compareTo(MyPair pair) {
        int thisValue = mWeight.intValue();
        int otherValue = pair.getValue().intValue();
        if (thisValue == otherValue) {
            return 0;
        } else if (thisValue > otherValue) {
            return 1;
        } else {
            return -1;
        }
    }

} // end of class MyPair
