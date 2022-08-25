public class TestPriorityQueue {
	public static void main(String[] args) {
		ERPriorityQueue testQueue = new ERPriorityQueue();
		testQueue.add("Jonas");
		System.out.println(testQueue.add("Jonas", 3)); //should output false - can't add the same name twice!!
		testQueue.add("Catherine");
		testQueue.add("Vanessa");
		System.out.println(testQueue.patients);
		System.out.println(testQueue.nameToIndex); //Vanessa=3, Catherine=2, Jonas=1

		testQueue.changePriority("Jonas", 100); //should not change nameToIndex - no upheap or downheap needed since Jonas
		//was already the root
		System.out.println(testQueue.patients); //order should still be Jonas, Catherine, Vanessa
		System.out.println(testQueue.nameToIndex);

		testQueue.remove("Catherine");
		//should end up with Jonas and Vanessa with indices 1 and 2
		System.out.println(testQueue.patients);
		System.out.println(testQueue.nameToIndex);

		testQueue.changePriority("Jonas", Double.POSITIVE_INFINITY); //should NOT downHeap Jonas

		System.out.println(testQueue.patients);
		System.out.println(testQueue.nameToIndex);

		testQueue.changePriority("Vanessa", 1); //should upheap Vanessa
		System.out.println(testQueue.patients); //Vanessa, Jonas
		System.out.println(testQueue.nameToIndex); //Vanessa = 1, Jonas = 2 
	}
}

