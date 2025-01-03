class AutocompleteSystem {
    HashMap<String,Integer> map;
    String search;
    TrieNode root;

    class TrieNode{
        TrieNode[] children;
        List<String> topResult;

        public TrieNode(){
            this.children=new TrieNode[256];
            this.topResult= new ArrayList<>();
        }
    }

    private void insert(String word){
        TrieNode curr=root;
        for(char c: word.toCharArray()){
            if(curr.children[c-' '] == null){
                curr.children[c-' ']=new TrieNode();
            }
            curr = curr.children[c-' '];

            List<String> list = curr.topResult;
            if(!list.contains(word)){
                list.add(word);
            }

            Collections.sort(list,(a,b)->{
                if(map.get(a)== map.get(b)){
                    return a.compareTo(b);
                }
                return map.get(b) - map.get(a);
            });

            if(list.size()>3){
                list.remove(list.size()-1);
            } 
        }
    }

    private List<String> searchprefix(String prefix){
        TrieNode curr=root;
        for(char c: prefix.toCharArray()){
            if(curr.children[c-' '] == null){
                return new ArrayList<>();
            }
            curr = curr.children[c-' '];
            
        }
        return curr.topResult;
    }
    public AutocompleteSystem(String[] sentences, int[] times) {
        this.map=new HashMap<>();
        this.search="";
        this.root= new TrieNode();
        for(int i=0;i<sentences.length;i++){
            String sentence=sentences[i];
            int time=times[i];
            
            map.put(sentence,map.getOrDefault(sentence,0)+time);
            insert(sentence);
        }
    }
    
    public List<String> input(char c) {
        List<String> result= new ArrayList<>();
        if(c=='#'){
            
            map.put(search,map.getOrDefault(search,0)+1);
            insert(search);
            search="";
            return result;
        }
        PriorityQueue<String> pq= new PriorityQueue<>((a,b)->{
            if(map.get(a)== map.get(b)){
                return b.compareTo(a);
            }
            return map.get(a)-map.get(b);
        });
        search+=c;
        return searchprefix(search);
    }
}

