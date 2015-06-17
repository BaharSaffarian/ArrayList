import java.util.Arrays;


public class MyArrayList <T> {
    private Object[] objects;
    private int currentIndex;
    private final int incrementSteps=5;

    public MyArrayList() {
        objects=new Object[incrementSteps];
        currentIndex=0;
    }

    public void add(T object){

        if(currentIndex == objects.length){
            acquireSpace();
        }
        objects[currentIndex]=object;
        currentIndex++;
    }

    public void add(T object, int index){
        if(index>currentIndex){
            return;
        }
        T[] tempArray= (T[]) Arrays.copyOfRange(objects,index,objects.length);
        objects[index]=object;
        currentIndex=index+1;
        for(int i=0;i<tempArray.length;i++){
            add(tempArray[i]);
        }
    }

    public T get(int index){
        T result=null;
        if(index<currentIndex){
            result= (T) objects[index];
        }
        return result;
    }

    public int getIndex(T object){
        int index=-1;
        for (int i = 0; i <currentIndex ; i++) {
            if(object.equals((T)objects[i])){
                index=i;
                break;
            }
        }
        return index;
    }
    public boolean contains(T object){
        if(getIndex(object)!=-1){
            return true;
        }
        return false;
    }

    public boolean remove(int index){
        if(index>-1 && index<currentIndex){
            for (int i = index; i <(currentIndex-1) ; i++) {
                objects[i]=objects[i+1];
            }
            currentIndex--;
            return true;
        }
        return false;
    }

    public boolean remove(T object){
        int index=getIndex(object);
        return remove(index);
    }

    public void reverse(){
        for (int i = 0; i <(objects.length/2) ; i++) {
            T temp=(T)objects[i];
            objects[i]=objects[(currentIndex-1)-i];
            objects[(currentIndex-1)-i]=temp;
        }
    }

    public void sort(boolean mode){
        objects=(T[]) Arrays.copyOf(objects,currentIndex);
        Arrays.sort(objects);
        if(!mode){
            reverse();
        }
    }

    private void acquireSpace(){
        objects= Arrays.copyOf(objects,objects.length+incrementSteps);
    }

    public String toString(){
        String str="{ ";
        for (int i=0; i<currentIndex;i++){
            str+=(objects[i]).toString()+" ";
        }
        str+="}";
        return str;
    }

    public static void main(String[] Args){
        MyArrayList<Integer> arrayList=new MyArrayList<Integer>();
        for (int i=0; i<10;i++){
            arrayList.add(i);
        }

        arrayList.add(13,5);
        System.out.println(arrayList.toString());
        System.out.println(arrayList.get(6).toString()+" , "+ (arrayList.get(15)==null ? "null" : arrayList.get(15).toString() ));
        System.out.println(arrayList.getIndex(13) + " , "+arrayList.contains(19));
        System.out.println(arrayList.remove(8)+" , "+ arrayList);
        System.out.println(arrayList.remove(new Integer(5))+" , "+ arrayList);
        arrayList.reverse();
        System.out.println(arrayList);
        arrayList.sort(true);
        System.out.println(arrayList);
        arrayList.sort(false);
        System.out.println(arrayList);
    }
}
