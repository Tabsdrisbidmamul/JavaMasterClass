package com.example;

public class Worker {
    private String name;
    private boolean active;

    public Worker(String name, boolean active) {
        this.name = name;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public synchronized void work(SharedResource sharedResource, Worker otherWorker) {

        while (active) {
            if(sharedResource.getOwner() != this) {
                try {
                    wait(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }

            if(otherWorker.isActive()) {
                System.out.println(this.name + " : give the resource to the worker " + otherWorker.getName());
                sharedResource.setOwner(otherWorker);
                /*
                * Whilst the example is simple - by removing the continue after "giving the resource" to the other
                * Thread we have removed the live lock is in this example - of course there are times when it is not
                * easy to see - but effectively we want to make sure that the thread is not active when it shouldn't
                * be - here the Thread basically ends it task and terminates itself after executing the critical
                * section of code
                *
                * */
//                continue;
            }

            System.out.println(this.name + " : working on the common resource");
            active = false;
            sharedResource.setOwner(otherWorker);
        }
    }
}
