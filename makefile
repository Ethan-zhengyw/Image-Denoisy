VPATH=src:bin

AddNoise.class DeNoise.class: DeNoise.java AddNoise.java ImageProcess.class 
	javac -classpath bin src/AddNoise.java -d bin
	javac -classpath bin src/DeNoise.java -d bin

ImageProcess.class: ImageProcess.java
	javac src/ImageProcess.java -d bin

clean:
	rm -rf bin/* src/*.swp src/*~ images/noisied/* images/denoisied/*
