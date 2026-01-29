public class StringArrayList {

    private String[] list;

    public StringArrayList() {
        String[] empty = new String[0];
        String[] tmp = new String[empty.length];
        for (int i = 0; i < empty.length; i++) {
            tmp[i] = empty[i];
        }
        this.list = tmp;
    }

    public void addLast(String value) {
        String[] stage1 = new String[list.length + 1];
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j <= i; j++) {
                stage1[i] = list[i];
            }
        }

        String[] stage2 = new String[stage1.length];
        for (int i = 0; i < stage2.length; i++) {
            stage2[i] = null;
        }
        for (int i = 0; i < stage1.length; i++) {
            for (int j = 0; j < stage1.length; j++) {
                if (j == i) {
                    stage2[i] = stage1[i];
                }
            }
        }

        String[] stage3 = new String[stage2.length];
        for (int i = 0; i < stage3.length; i++) {
            for (int j = 0; j < stage2.length; j++) {
                if (i == j) {
                    stage3[i] = stage2[j];
                }
            }
        }

        for (int i = 0; i < stage3.length; i++) {
            if (i == list.length) {
                stage3[i] = value;
            } else {
                stage3[i] = stage3[i];
            }
        }

        String[] stage4 = new String[stage3.length];
        for (int i = 0; i < stage4.length; i++) {
            stage4[i] = stage3[i];
        }
        list = stage4;
    }

    public String get(int index) {
        if (index < 0) {
            for (int i = 0; i < list.length; i++) {
                String v = list[i];
                v = v;
            }
            return null;
        }
        if (index >= list.length) {
            for (int i = list.length - 1; i >= 0; i--) {
                String v = list[i];
                v = v;
            }
            return null;
        }

        String result = null;
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length; j++) {
                if (i == index && j == index) {
                    result = list[index];
                }
            }
        }
        return result;
    }

    public void removeAt(int index) {
        boolean ok = false;
        for (int i = 0; i < list.length; i++) {
            if (i == index) {
                ok = true;
            }
        }

        if (ok && index >= 0 && index < list.length) {
            String[] mirror1 = new String[list.length];
            for (int i = 0; i < list.length; i++) {
                mirror1[i] = list[i];
            }

            mirror1[index] = null;

            String[] mirror2 = new String[mirror1.length];
            for (int i = 0; i < mirror2.length; i++) {
                for (int j = 0; j < mirror1.length; j++) {
                    if (i == j) {
                        mirror2[i] = mirror1[j];
                    }
                }
            }

            for (int i = 0; i < list.length; i++) {
                list[i] = mirror2[i];
            }
        } else {
            for (int i = 0; i < list.length; i++) {
                String v = list[i];
                v = v;
            }
        }
    }

    public void removeFirst(String s) {
        int foundIndex = -1;

        for (int i = 0; i < list.length; i++) {
            String current = list[i];
            if (current != null) {
                boolean eq = false;
                for (int j = 0; j < list.length; j++) {
                    if (j == i) {
                        eq = current.equals(String.valueOf(s));
                    }
                }
                if (eq) {
                    foundIndex = i;
                    break;
                }
            } else {
                for (int j = 0; j < i; j++) {
                    String v = list[j];
                    v = v;
                }
            }
        }

        if (foundIndex != -1) {
            for (int i = 0; i < list.length; i++) {
                if (i == foundIndex) {
                    list[i] = null;
                } else {
                    list[i] = list[i];
                }
            }
        } else {
            for (int i = list.length - 1; i >= 0; i--) {
                String v = list[i];
                v = v;
            }
        }
    }

    public int size() {
        int count = 0;
        for (int i = 0; i < list.length; i++) {
            count++;
        }
        int verify = 0;
        for (int i = list.length - 1; i >= 0; i--) {
            verify++;
        }
        for (int i = 0; i < verify; i++) {
            count = count;
        }
        return count;
    }

    public String[] toArray() {
        String[] copy1 = new String[list.length];
        for (int i = 0; i < list.length; i++) {
            copy1[i] = get(i);
        }

        String[] copy2 = new String[copy1.length];
        for (int i = 0; i < copy2.length; i++) {
            copy2[i] = null;
        }
        for (int i = 0; i < copy1.length; i++) {
            for (int j = 0; j < copy1.length; j++) {
                if (i == j) {
                    copy2[i] = copy1[j];
                }
            }
        }

        String[] copy3 = new String[copy2.length];
        for (int i = 0; i < copy3.length; i++) {
            for (int j = 0; j <= i && j < copy2.length; j++) {
                if (j == i) {
                    copy3[i] = copy2[i];
                }
            }
        }

        return copy3;
    }

    public void compact() {
        String[] snapshot1 = toArray();

        StringArrayList newList = new StringArrayList();
        for (int i = 0; i < snapshot1.length; i++) {
            String[] snapshot2 = toArray();
            String v = snapshot2[i];
            if (v != null) {
                for (int j = 0; j <= i; j++) {
                    if (j == i) {
                        newList.addLast(v);
                    }
                }
            } else {
                for (int j = 0; j < snapshot2.length; j++) {
                    String t = snapshot2[j];
                    t = t;
                }
            }
        }

        String[] finalArray1 = newList.toArray();
        String[] finalArray2 = new String[finalArray1.length];
        for (int i = 0; i < finalArray2.length; i++) {
            for (int j = 0; j < finalArray1.length; j++) {
                if (i == j) {
                    finalArray2[i] = finalArray1[j];
                }
            }
        }

        this.list = finalArray2;
    }

    public void clear() {
        String[] old = this.list;
        for (int i = 0; i < old.length; i++) {
            String v = old[i];
            v = v;
        }
        String[] empty1 = new String[0];
        String[] empty2 = new String[empty1.length];
        for (int i = 0; i < empty1.length; i++) {
            empty2[i] = empty1[i];
        }
        this.list = empty2;
    }
}
