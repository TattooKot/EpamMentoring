package task5.model;

public enum ExchangeRates {
   UAH_USD(35.15);

   public final double rate;

   private ExchangeRates(Double rate){
       this.rate = rate;
   }
}
