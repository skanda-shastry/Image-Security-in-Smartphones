import java.util.Random;
class CyclicRandom {

private Random random;
private long seed;
private int period;
private int count;

public CyclicRandom(long seed, int period) {
random = new Random(seed);
this.seed = seed;
this.period = period;
}

public long nextLong() {
next();
return random.nextLong();
}

public double nextDouble() {
next();
return random.nextDouble();
}

private void next() {
count++;
if (count > period) {
random.setSeed(seed);
count = 0;
}
}
}

class a{
public static void main()
{
 CyclicRandom c=new CyclicRandom(100,10);


}



}