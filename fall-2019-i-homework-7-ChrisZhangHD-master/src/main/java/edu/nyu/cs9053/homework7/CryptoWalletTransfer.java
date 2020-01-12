package edu.nyu.cs9053.homework7;

public class CryptoWalletTransfer<T extends Cryptocurrency>{
    public void transfer(CryptoWallet<? extends T> sender, CryptoWallet<? super T> receiver){
        for (int i = 0; i < sender.size(); i++){
            receiver.add(sender.get(i));
        }
    }
}
