
package charlie.model;


import java.util.Comparator;

public class ScoreComparator implements Comparator<User> 
{
        @Override
        public int compare(User score1, User score2) 
        {

            double sc1 = score1.getStash().getMoney();
            double sc2 = score2.getStash().getMoney();

            if (sc1 > sc2)
            {
                return -1;
            }
            else if (sc1 < sc2)
            {
                return +1;
            }
            else
            {
                return 0;
            }
        }
}      
