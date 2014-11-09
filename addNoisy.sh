rm images/noisied/*
java -classpath bin AddNoise

rm images/denoisied/*
java -classpath bin DeNoise
