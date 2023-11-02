class SortingDemo {
  private static void swap(int a[], int i, int j) { // swap array elements i and j
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }


  // https://visualgo.net/en/sorting?slide=7-1
  private static void bubbleSort(int a[], int N) { // the standard version
    for (; N > 0; --N) // N iterations
      for (int i = 0; i < N-1; ++i) // except the last, O(N)
        if (a[i] > a[i+1]) // not in non-decreasing order
          swap(a, i, i+1); // swap in O(1)
  }


  // https://visualgo.net/en/sorting?slide=11-2
  private static void merge(int a[], int low, int mid, int high) {
    // subarray1 = a[low..mid], subarray2 = a[mid+1..high], both sorted
    int N = high-low+1;
    int[] b = new int[N]; // discuss: why do we need a temporary array b?
    int left = low, right = mid+1, bIdx = 0;
    while (left <= mid && right <= high) // the merging
      b[bIdx++] = (a[left] <= a[right]) ? a[left++] : a[right++];
    while (left <= mid) b[bIdx++] = a[left++]; // leftover, if any
    while (right <= high) b[bIdx++] = a[right++]; // leftover, if any
    for (int k = 0; k < N; ++k) a[low+k] = b[k]; // copy back
  }

  // https://visualgo.net/en/sorting?slide=11-5
  private static void mergeSort(int a[], int low, int high) {
    // the array to be sorted is a[low..high]
    if (low < high) { // base case: low >= high (0 or 1 item)
      int mid = (low+high) / 2; 
      mergeSort(a, low  , mid ); // divide into two halves
      mergeSort(a, mid+1, high); // then recursively sort them
      merge(a, low, mid, high); // conquer: the merge routine
    }
  }
}
