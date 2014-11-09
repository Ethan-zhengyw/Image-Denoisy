VPATH=src:bin

FindBetterParam.class: FindBetterParam.java ImageDenosing_SA.class
	javac -classpath bin src/FindBetterParam.java -d bin

AddNoise.class DeNoise.class ImageDenosing_SA.class: DeNoise.java AddNoise.java ImageProcess.class ImageDenosing_SA.java
	javac -classpath bin src/AddNoise.java -d bin
	javac -classpath bin src/DeNoise.java -d bin
	javac -classpath bin src/ImageDenosing_SA.java -d bin

ImageProcess.class: ImageProcess.java
	javac src/ImageProcess.java -d bin

clean:
	rm -rf bin/* src/*.swp src/*~ images/noisied/* images/denoisied/*
