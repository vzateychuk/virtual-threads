# Virtual threads vs platform threads

Compare execution of 10K tasks (each is waiting for 1 sec) with classical platform thread vs new virtual thread

## Result
Classical 'platform' thread executor with 256 threads execution time = 40,263 ms
New 'virtual thread per task' executor, time = 1,559 ms

Note! the project run with **--enable-preview** setting in gradle