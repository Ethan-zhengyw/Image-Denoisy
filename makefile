VPATH=src

AddNoise.class: AddNoise.java ImageProcess.class
	javac -classpath bin src/AddNoise.java -d bin

ImageProcess.class: ImageProcess.java
	javac src/ImageProcess.java -d bin

clean:
	rm -rf bin/* src/*.swp src/*~ images/noisied/* images/denoisied/*
