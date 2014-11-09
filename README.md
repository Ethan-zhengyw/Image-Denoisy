Image-Denoisy
=============

an group assignment for AI course

### Goal

  1. Add 10% noise to all the images in images/origin
  2. Implement the denoising algorithm base on Simulated annealing algorithm
  3. Train three parameters(H B N) in the algorithm

### Add noisie & Denoisy
    
  1. add noise to images in images/origin and save to images/noisied
  
    ```java
    java -classpath bin AddNoise
    ```
  2. denoising api
  
    ```java
    ImageDenoising_SA.ImageDenoising(h, b, n, path_to_noisied_image)
    ```
    
    be called in .java file


### Trainning parameters

  1. This script create 5 thread base on H to execute a java command
    
      Run practice script and write record in result.log
    
      ```shell
      nohup time ./practice.sh > result.log &
      ```
      
  2. Default H range: from 0.0 to 0.5 step by 0.1
  3. modify the range of H

      ```shell
      h=0.1; b=0.1; n=0.1;
      H=0.5; B=10;  N=10;
      step=0.1
      ```
      
      *only three place should be changed: h, H and step*
      
      *please make sure that the script won't produce too many threads* 
    
### After trainning 


1.  Use the following command to find the most important message from result.log
    
    ```shell
    ./show_result.sh | tail
    ```
    
    ex. result
    
    ```txt
    h=0.1 b=2.1000000000000005 n=6.699999999999992|average_okRate=96.40352602358219   
    h=0.0 b=1.5000000000000002 n=3.1000000000000014|average_okRate=96.40352602358219
    h=0.3 b=1.9000000000000006 n=4.100000000000001|average_okRate=96.4036955656829
    h=0.4 b=1.9000000000000006 n=4.200000000000001|average_okRate=96.40490496600115
    ```
    
    The parameters combination in the last line(with maximum okRate) will be selected.
