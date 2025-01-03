class PhoneDirectory {
    HashSet<Integer> set;
    Queue<Integer> q;
    public PhoneDirectory(int maxNumbers) {
        this.set=new HashSet<>();
        this.q= new LinkedList<>();
        for(int i=0;i<maxNumbers;i++){
            q.add(i);
            set.add(i);
        }
    }
    
    public int get() {
        if(q.isEmpty()) return -1;
        int num=q.poll();
        set.remove(num);
        return num;

    }
    
    public boolean check(int number) {
        return set.contains(number); 
    }
    
    public void release(int number) {
        if(!set.contains(number)){
            q.add(number);
            set.add(number);
        }
    }
}

