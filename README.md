## Some observations

looping through a list of size 1000 and printing those,
we had the normal stream() taking less time as compared to parallelStream().

Computer is having 8 cores and still on using parallelStream(), it took more time when ideally under laymans terms, parallel execution should take less time.
There is a overhead of creating and managing threads here.

On doing the same test on a list size of 10 million,

stream() - took 49.3 s

parallelStream() - took 79s

Now, we split the whole list into sublist of chunk size of 10000 and used completableFuture to process them all parallel.

completableFuture - 83.3s

On using a chunk size of 100000

completableFuture - 85.5s

On using a chunk size of 100

completableFuture - 84s
