package wxdgaming.boot.springstarter.scripts;

import org.springframework.stereotype.Service;

@Service
public class B2 {
    private int b = 1;

    public B2() {
        System.out.println("\n\n" + this.getClass() + "\n\n");
    }

}
