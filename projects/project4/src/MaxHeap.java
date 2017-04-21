/**
 * Created by HanWang on 4/17/17.
 */
public class MaxHeap{// class
    private int[] Heap;// declaring required variables
    private int[] sort;
    private int size;
    private int maxsize;
    private static final int FRONT = 1;
    public MaxHeap(int maxsize){// constructor to initialize arrays
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new int[this.maxsize + 1];
        Heap[0] = Integer.MAX_VALUE;
        sort=new int[10];
    }
    private int parent(int pos){ // method to find parent to a child
        return pos / 2;
    }
    private int leftChild(int pos){// // method to find left child of a node
        return (2 * pos);
    }
    private int rightChild(int pos){// method to find right child of a node
        return (2 * pos) + 1;
    }
    private boolean isLeaf(int pos){// method to check a node is leaf or not
        if (pos >= (size / 2) && pos <= size){
            return true;
        }
        return false;
    }
    private void swap(int fpos,int spos){// method to swap elements for UP heap
        int tmp;
        tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    }
    private void maxHeapify(int pos){// method to heapify i.e. to array
        if (!isLeaf(pos)){
            if ( Heap[pos] < Heap[leftChild(pos)] || Heap[pos] < Heap[rightChild(pos)]){
                if (Heap[leftChild(pos)] > Heap[rightChild(pos)]){
                    swap(pos, leftChild(pos));
                    maxHeapify(leftChild(pos));
                }else{
                    swap(pos, rightChild(pos));
                    maxHeapify(rightChild(pos));
                }
            }
        }
    }
    public void insert(int element){// method to insert elements into heap
        Heap[++size] = element;
        int current = size;
        while(Heap[current] > Heap[parent(current)]){
            swap(current,parent(current));
            current = parent(current);
        }
    }
    public void print(){// displaying heap
        for (int i = 1; i <= size / 2; i++ ){
            System.out.print(" PARENT : " + Heap[i] + " LEFT CHILD : " + Heap[2*i]+ " RIGHT CHILD :" + Heap[2 * i + 1]);
            System.out.println();
        }
    }
    public void maxHeap(){// method for heapify
        for (int pos = (size / 2); pos >= 1; pos--){
            maxHeapify(pos);
        }
    }
    public void sort(){ // method to sort array to find median
        int temp;
        for(int i=0;i<Heap.length;i++)
            if(Heap[i]!=0)
                sort[i]=Heap[i];
        for(int i=0; i < sort.length; i++){
            for(int j=1; j < sort.length; j++){
                if(sort[j-1] < sort[j]){
                    temp = sort[j-1]; //swap elements
                    sort[j-1] = sort[j];
                    sort[j] = temp;
                }
            }
        }
    }
    public static int[] removeElement(int[] original, int element){ // method to remove an element from array
        int[] n = new int[original.length - 1];
        System.arraycopy(original, 0, n, 0, element );
        System.arraycopy(original, element+1, n, element, original.length - element-1);
        return n;
    }
    public static void main(String...arg){// main method
        System.out.println("The Max Heap is ");
        MaxHeap maxHeap = new MaxHeap(15);// intializing heap with size 15
        maxHeap.insert(5);// inserting elements heap
        maxHeap.insert(3);
        maxHeap.insert(17);
        maxHeap.insert(10);
        maxHeap.insert(84);
        maxHeap.insert(19);
        maxHeap.insert(6);
        maxHeap.insert(22);
        maxHeap.insert(9);
        maxHeap.maxHeap();
        maxHeap.print();
        System.out.println("The max val is " + maxHeap.Heap[1]);// getting max value of heap, i.e. first element
        maxHeap.sort();// sorting array
        int len=1;
        for(int i=0;i<maxHeap.sort.length;i++) // finding middle element for median
            if (maxHeap.sort[i]!=0)
                ++len;
        int mid=len/2;
        if(len%2!=0)
            System.out.println("Median of the heap is : "+maxHeap.sort[mid]);
        else
            System.out.println("Median of the heap is : "+(maxHeap.sort[mid]+maxHeap.sort[mid+1])/2);
        maxHeap.sort=removeElement(maxHeap.sort, mid);// deleting median
        System.out.println("After removing Median");
        for(int i=1;i<9;i++)// displaying list after deleting median
            System.out.print(maxHeap.sort[i]+" ");
    }// end of main method
}// end of class
