package dod;


import java.util.ArrayList;
import java.util.Random;

    class Producer extends Thread {
        private Buffer _buff;
        private int _turns_no;
        private Random gen = new Random();

        public Producer(Buffer buff, int turns_no) {
            _buff = buff;
            _turns_no = turns_no;
        }

        public void run() {
            int bufferSize = _buff.getBufferMaxSize();
            for (int i = 0; i < _turns_no; ++i) {
                _buff.put(i);
                try {
                    sleep(gen.nextInt(50));
                } catch (Exception ex) {}
            }

            if (_turns_no % bufferSize != 0) {
                for (int i=_turns_no % bufferSize;i< bufferSize;i++) {
                    _buff.put(null);
                }
            }
        }
    }

    class Consumer extends Thread {
        private Buffer _buff;
        private int _turns_no;
        private Random gen = new Random();

        public Consumer(Buffer buff, int turns_no) {
            _turns_no = turns_no;
            _buff = buff;
        }

        public void run() {
            for (int i = 0; i < _turns_no; ++i) {
                Integer val = _buff.get();
                if (val != null) {
                    System.out.println("Read value:" + val);
                }
                try {
                    sleep(gen.nextInt(50));
                } catch (Exception ex) {}
            }
        }
    }

    class Processor extends Thread {
        private Buffer _buff;
        private int _turns_no;
        private int _processor_no;

        public Processor(Buffer buff, int turns_no, int processor_no) {
            _buff = buff;
            _processor_no = processor_no;
            _turns_no = turns_no;
        }

        public void run () {
            for (int i=0;i<_turns_no;i++) {
                _buff.process(_processor_no);
            }

            int buffSize = _buff.getBufferMaxSize();
            if (_turns_no % buffSize != 0) {
                for (int i=_turns_no % buffSize;i < buffSize; i++) {
                    _buff.process(_processor_no);
                }
            }
        }
    }

    class Buffer {
        private ArrayList<Integer> _buff;
        private int indx = 0;
        private int stage = 0;
        private int maxSize;
        private int lastStage;

        public Buffer(int size, int processors_no) {
            maxSize = size;
            lastStage = processors_no + 1;
            _buff = new ArrayList<>(maxSize);
        }

        public int getBufferMaxSize() {
            return maxSize;
        }

        public synchronized void put(Integer i) {
            while (stage != 0) {
                try {
                    wait();
                } catch (Exception ex) {}
            }
            _buff.add(i);
            indx++;

            if (indx == maxSize) {
                indx = 0;
                stage++;
            }
            notifyAll();
        }

        public synchronized void process(int processor_no) {
            while (stage != processor_no) {
                try {
                    wait();
                } catch (Exception ex) {}
            }

            Integer val = _buff.get(indx);
            if (val != null) {
                _buff.set(indx, val+processor_no);
            }
            indx++;

            if (indx == maxSize) {
                indx = 0;
                stage++;
            }
            notifyAll();
        }

        public synchronized Integer get() {
            while (stage != lastStage) {
                try {
                    wait();
                } catch (Exception ex) { }
            }

            Integer val = _buff.get(indx);
            indx++;

            if (indx == maxSize) {
                stage = 0;
                indx = 0;
            }
            notifyAll();

            return val;
        }
    }



