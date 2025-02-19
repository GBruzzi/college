#include <stdio.h>

int mergeAndCount(int arr[], int temp[], int left, int mid, int right) {
    int i, j, k;
    int inv_count = 0;

    i = left;    
    j = mid + 1; 
    k = left;    

    while ((i <= mid) && (j <= right)) {
        if (arr[i] <= arr[j]) {
            temp[k++] = arr[i++];
        } else {
            temp[k++] = arr[j++];
            inv_count += (mid - i + 1); 
        }
    }

    while (i <= mid) {
        temp[k++] = arr[i++];
    }

    while (j <= right) {
        temp[k++] = arr[j++];
    }

    for (i = left; i <= right; i++) {
        arr[i] = temp[i];
    }

    return inv_count;
}

int mergeSortAndCount(int arr[], int temp[], int left, int right) {
    int mid, inv_count = 0;
    if (right > left) {
        mid = (right + left) / 2;

        inv_count += mergeSortAndCount(arr, temp, left, mid);
        inv_count += mergeSortAndCount(arr, temp, mid + 1, right);

        inv_count += mergeAndCount(arr, temp, left, mid, right);
    }
    return inv_count;
}

int main() {
    int n;

    while (scanf("%d", &n) != EOF) {
        int largada[n], chegada[n];
        int temp[n];
        int pos[n+1]; 

        for (int i = 0; i < n; i++) {
            scanf("%d", &largada[i]);
        }

        for (int i = 0; i < n; i++) {
            scanf("%d", &chegada[i]);
            pos[chegada[i]] = i;
        }

        for (int i = 0; i < n; i++) {
            largada[i] = pos[largada[i]];
        }
        int result = mergeSortAndCount(largada, temp, 0, n - 1);

        printf("%d\n", result);
    }

    return 0;
}
