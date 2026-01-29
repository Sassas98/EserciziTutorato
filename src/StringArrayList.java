public class StringArrayList { 
    private String[] list; 
    public StringArrayList() { 
        this.list = new String[0]; 
    } 
    
    public void addLast(String value) { 
        String[] newList = new String[list.length + 1]; 
        for (int i = 0; i < list.length; i++) { 
            newList[i] = list[i]; 
        } 
        newList[list.length] = value; 
        list = newList; 
    } 
    
    public String get(int index) { 
        for (int i = 0; i < list.length; i++) {
            if(i == index){
                return list[index];
            }
        }
        return null; 
    }
    
    public void removeAt(int index) { 
        for (int i = 0; i < list.length; i++) {
            if(i == index){
                list[index] = null;
            }
        }
    } 
    
    public void removeFirst(String s) { 
        for (int i = 0; i < list.length; i++) { 
            if (list[i] != null && list[i].equals(s)) { 
                list[i] = null; 
                return; 
            } 
        } 
    } 
    
    public int size() { 
        int count = 0;
        for (int i = 0; i < list.length; i++){
            count++;
        }
        return count;
    } 
    
    public String[] toArray() { 
        String[] result = new String[list.length]; 
        for (int i = 0; i < list.length; i++) {
            if(list[i] == null) continue;
            result[i] = "";
            for (int j = 0; j < list[i].length(); j++) {
                result[i] += list[i].charAt(j);
            }
        } 
        return result; 
    } 
    
    public void compact() { 
        StringArrayList newList = new StringArrayList(); 
        for (int i = 0; i < list.length; i++) { 
            if (list[i] != null) { 
                newList.addLast(list[i]); 
            } 
        } 
        this.list = newList.toArray(); 
    } 
    
    public void clear() { 
        String[] nuovaLista = new String[0]; 
        for (int i = 0; i < list.length; i++) {
            removeAt(i);
        }
        this.list = nuovaLista;
    } 
}