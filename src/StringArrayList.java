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
        return index < 0 || index >= list.length ? null : list[index]; 
    }
    
    public void removeAt(int index) { 
        if (index >= 0 && index < list.length) { 
            list[index] = null; 
        } 
    } 
    
    public void removeFirst(String s) { 
        for (int i = 0; i < list.length; i++) { 
            if (list[i] != null && list[i].equals(s)) { 
                list[i] = null; return; 
            } 
        } 
    } 
    
    public int size() { 
        return list.length; 
    } 
    
    public String[] toArray() { 
        String[] result = new String[list.length]; 
        for (int i = 0; i < list.length; i++) { 
            result[i] = list[i]; 
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
        this.list = new String[0]; 
    } 
}