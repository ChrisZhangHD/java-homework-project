package edu.nyu.cs9053.homework7;

import java.util.concurrent.atomic.AtomicReference;

public class Wallet<T>{

    private static final int DEFAULT_ARRAY_SIZE = 16;

    private final AtomicReference<T[]> array;

    private final ArrayCreator<T> arrayCreator;

    private int walletSize = 0;


    protected Wallet(ArrayCreator<T> arrayCreator) {
        this(arrayCreator, DEFAULT_ARRAY_SIZE);
    }

    protected Wallet(ArrayCreator<T> arrayCreator, int defaultSize){
        this.arrayCreator = arrayCreator;
        this.array = new AtomicReference<>(grow(defaultSize));
    }

    private T[] grow(int defaultSize){
        return arrayCreator.create(defaultSize);
    }

    public boolean add(T item){
        if (item == null){
            return false;
        }
        if (contains(item)){
            return false;
        }
        T[] walletItemsArray = array.get();
        if(walletSize < walletItemsArray.length){
            walletItemsArray[walletSize] = item;
        }else {
            T[] newWalletItemsArray = arrayCreator.create(walletItemsArray.length + 1);
            System.arraycopy(walletItemsArray, 0, newWalletItemsArray, 0, walletItemsArray.length);
            newWalletItemsArray[walletItemsArray.length] = item;
            walletItemsArray = newWalletItemsArray;
        }
        walletSize += 1;
        array.set(walletItemsArray);
        return true;

    }

    public boolean contains(T item){
        if (item == null){
            return false;
        }
        T[] walletItemsArray = array.get();
        for (T walletItem : walletItemsArray) {
            if (walletItem != null && walletItem.equals(item)) {
                return true;
            }
        }
        return false;
    }

    public boolean remove(T item){
        if (item == null){
            return false;
        }
        T[] walletItemsArray = array.get();
        if (contains(item)){
            int itemIndex = -1;
            for (int i = 0; i < walletItemsArray.length; i++){
                if (walletItemsArray[i] != null && walletItemsArray[i].equals(item)){
                    itemIndex = i;
                    break;
                }
            }
            System.arraycopy(walletItemsArray, itemIndex + 1, walletItemsArray, itemIndex, walletSize - itemIndex - 1);
            walletSize -= 1;
            array.set(walletItemsArray);
            return true;
        }else {
            return false;
        }

    }

    public T get(int index){
        T[] walletItemsArray = array.get();
        if (index < 0 || index >= walletSize){
            return null;
        }else {
            return walletItemsArray[index];
        }


    }

    public int size(){
        return walletSize;
    }
}
