import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


public class PrimeUtil {
  
  static final int FIRST_PRIME = 2;
  
  
  public static void main( String[] args ) throws Exception{
    
    
    long time = System.currentTimeMillis();
    ArrayList<Integer> results = EratosthenesSieve(10000);
    time = System.currentTimeMillis() - time;
    
    
    Iterator<Integer> itr = results.iterator();
    while ( itr.hasNext() ){
      System.out.printf( "%d ", itr.next() );
    }
    
    
    System.out.println("\nTime cost = " + time + " ms");
    
  }
  
  
  public static ArrayList<Integer> EratosthenesSieve(int max) throws Exception{
    
    int currentRemove;
    int currentPrime = FIRST_PRIME;
    int size = max - 1;
    
    
    // Check input correctness
    if ( size <= 0 ) {
      throw new Exception("Min number must be smaller than max number");
    }
    
    
    // Initialize candidates with true as default value
    ArrayList<Boolean> candidates = 
        new ArrayList<Boolean>( Collections.nCopies(size + 1, true) );
    
    
    // Main loop
    while ( true ){
      
      int i = currentPrime;
      // Remove all products of currentPrime
      while( (currentRemove = currentPrime * i++) < max){
        candidates.set(currentRemove - FIRST_PRIME, false);
      }
      
      
      // Find next prime
      int idx = currentPrime - FIRST_PRIME + 1;
      while( !candidates.get(idx) ){
        idx++;
      }
      currentPrime = idx + FIRST_PRIME;
      
      
      // Break if new prime square greater than max
      if ( currentPrime * currentPrime > max ){
        break;
      }
      
      
    }

    
    // Collect all primes from candidates
    ArrayList<Integer> results = new ArrayList<Integer>();
    for ( int i = 0; i < max - FIRST_PRIME; i++ ){
      if ( candidates.get(i) ){
        results.add( i + FIRST_PRIME );
      }
    }
    
    
    return results;
  }
  
  
}
