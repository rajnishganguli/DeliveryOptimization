# DeliveryOptimization
The project finds optimal path from rider to deliver K orders to customers from restaurants.

<img width="318" alt="image" src="https://github.com/rajnishganguli/DeliveryOptimization/assets/15128553/1e05be63-86cc-48c7-8e93-c69797654ac5">

## Assumptions
* Delivery is on earth, So earth's radius is used for computing distances.

## Notes
* We can take the input from commandline or API. I have hardcoded the values in Main class for 2 orders. The inpute parameters are : 
  * Coordinates of Aman, Restaurants and Customers
  * Preparation time of meal for each restaurants.
  * Aman's speed 

## Approach
* The code is generic and would work for any number of orders . (I have tested it for 3 restaurants and 3 customers)
* If you change the input in main method of Main class it will work for any number of orders.
* For 2 orders(K), the possible routes that Aman may take would be:
     *                 {"Aman", "R1", "C1", "R2", "C2"},
     *                 {"Aman", "R2", "C2", "R1", "C1"},
     *                 {"Aman", "R1", "R2", "C1", "C2"},
     *                 {"Aman", "R1", "R2", "C2", "C1"},
     *                 {"Aman", "R2", "R1", "C1", "C2"},
     *                 {"Aman", "R2", "R1", "C2", "C1"}
* In first two cases order is delivered one by one and in the last four cases order is delivered by picking both the orders together.
* Note that
  * Whenever a order from a restaurant is picked and the preparation time pt for that order is greater than the total time taken till that point of time then after picking the order the total time has to be equal to the preparation time pt else the total time is the amount of time the rider took to reach there.
  * The optimal path would be the path that takes minimum amount of time.

![image](https://github.com/rajnishganguli/DeliveryOptimization/assets/15128553/0a4d47d7-4308-4373-b5a2-ff45c8465555)

 
## How to run the program.
* Run the main method inside the Main class.
* Any input changes like coordinate changes or increasing the number of restaurants/customers can be done there.



