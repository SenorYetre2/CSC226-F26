package week1;

public class Counter {

    // 2. Add a private field
    // TODO: Declare private int count;
    private int count = 0;
    // 3. Write the default constructor
    public Counter() {
        count = 0;
        KeepTrack(count);
    }
    // 4. Add an alternate constructor
    public Counter(int count){
        this.count = count;
        KeepTrack(count);
    }
    // 5. Write an accessor method
    public int getCount() {
        return this.count;
    }
    // 6. Add an update method
    public increment() { 
        count++;
        KeepTrack(count);
    }
    // 7. Overload increment
   public increment(int count){
    this.count += count;
    KeepTrack(this.count);
   }
    // 8. Add a reset method
    public resetCount() { 
        this.count = 0;
        KeepTrack(this.count);
    }
    //9. Add a fixed-size history array that records the last 5 states of the counter. All logic must happen inside of the object and be hidden from the user.
    ArrayList<Integer> history = new ArrayList<>(5);
    private KeepTrack(int newNumber) { 
        history.add(0, newNumber);
        if (history.size() > 5) { 
            history.remove(5);
        }
    }
            

    
}