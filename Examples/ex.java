public interface check {
    public boolean checkCond(int num);
}
public class isPositive implements check {
    public boolean checkCond(int num) {
        return num >= 0;
    }
}

public  class isEven implements check {
    public boolean checkCond(int num) {
        return num % 2 == 0;
    }
}

public void printValue(int numbers[], check check){
    for(int num : numbers)
            if(check.checkCond(num))
                System.out.println(num);
}

public static void main(String[] args) {
    int numbers[] = {1,2,3,4,5,6,7,8,9,10};
    printValue(numbers, new isPositive());
    printValue(numbers, new isEven());
}

