package www.ds;

import java.util.Arrays;

//基于数组
public class SequenceArrayImpl implements Sequence {
    //存放默认数组
    private Object[] elementData;
    //默认容量
    private final static int DEFAULT_CAPACITY = 10;
    //存放的元素个数
    private int size;
    //线性表的最大容量
    private final static int MAX_CAPACIY = Integer.MAX_VALUE - 8;
    public SequenceArrayImpl() {
        //初始化存储元素数组，初始化为10
        this.elementData = new Object[DEFAULT_CAPACITY];
    }
    public SequenceArrayImpl(int capacity) {
        if(capacity > 0) {
            this.elementData = new Object[capacity];
        }
    }
    @Override
    public void add(Object data) {
        //首先判断添加元素是否越界
        ensureCapacityInternal(size + 1);
        elementData[size++] = data;
    }

    @Override
    public boolean remove(int index) {
        rangCheck(index);
        int moveSteps = size - index - 1;
        if(moveSteps > 0) {
            System.arraycopy(elementData,index + 1,elementData,index,moveSteps);
        }
        //数组最后一个元素
        elementData[--size] = null;
        return false;
    }

    @Override
    public Object get(int index) {
        rangCheck(index);
        return elementData[index];
    }

    @Override
    public boolean contains(Object data) {
        //判断是否有指定内容
        if (data == null) {
            for(int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return true;
                }
            }
        }else {
            for(int i = 0; i < size; i++) {
                if(data.equals(elementData[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Object set(int index, Object newData) {
        rangCheck(index);
        Object oldData = elementData[index];
        elementData[index] = newData;
        return oldData;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        this.size = 0;
    }

    @Override
    public Object[] toArray() {
        return this.elementData;
    }
    private void ensureCapacityInternal(int cap) {
        if(cap - elementData.length > 0) {
            //扩容策略
            grow(cap);
        }
    }
    private void grow(int cap) {
        int oldcap = elementData.length;
        int newcap = oldcap << 1;
        if(cap - newcap > 0) {
            newcap = cap;
        }
        if(newcap - MAX_CAPACIY > 0) {
            throw new IndexOutOfBoundsException("线性表超出最大直");
        }
        //数组扩容
        elementData = Arrays.copyOf(elementData,newcap);
    }
    private void rangCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("索引非法!");
        }
    }
}
