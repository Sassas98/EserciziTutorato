public class ReportSondaggio {

    public String analizzaRisultati(String titolo, String[] risposte) {
        if (titolo == null) return "ERRORE: titolo nullo";
        if (risposte == null) return "ERRORE: risposte nulle";

        String t = titolo.trim();
        if (t.length() == 0) t = "(sondaggio)";

        String[] pulite = new String[risposte.length];
        int vuote = 0;
        int valide = 0;

        for (int i = 0; i < risposte.length; i++) {
            String r = risposte[i];
            if (r == null) {
                pulite[i] = "";
                vuote++;
                continue;
            }
            r = r.trim();
            if (r.length() == 0) {
                pulite[i] = "";
                vuote++;
                continue;
            }

            String low = "";
            for (int k = 0; k < r.length(); k++) {
                char c = r.charAt(k);
                if (c >= 'A' && c <= 'Z') c = (char) (c - 'A' + 'a');
                low = low + c;
            }

            String norm = "";
            boolean ultimoSpazio = false;
            for (int k = 0; k < low.length(); k++) {
                char c = low.charAt(k);
                boolean spazio = (c == ' ' || c == '\t' || c == '\n' || c == '\r');
                if (spazio) {
                    if (!ultimoSpazio) {
                        norm = norm + ' ';
                        ultimoSpazio = true;
                    }
                } else {
                    norm = norm + c;
                    ultimoSpazio = false;
                }
            }
            norm = norm.trim();
            if (norm.length() == 0) {
                pulite[i] = "";
                vuote++;
            } else {
                pulite[i] = norm;
                valide++;
            }
        }

        String[] opzioni = new String[valide];
        int[] conteggi = new int[valide];
        int opzioniCount = 0;

        for (int i = 0; i < pulite.length; i++) {
            String r = pulite[i];
            if (r.length() == 0) continue;

            int pos = -1;
            for (int j = 0; j < opzioniCount; j++) {
                if (opzioni[j].equals(r)) {
                    pos = j;
                    break;
                }
            }
            if (pos == -1) {
                opzioni[opzioniCount] = r;
                conteggi[opzioniCount] = 1;
                opzioniCount++;
            } else {
                conteggi[pos]++;
            }
        }

        for (int i = 0; i < opzioniCount; i++) {
            int best = i;
            for (int j = i + 1; j < opzioniCount; j++) {
                if (conteggi[j] > conteggi[best]) best = j;
            }
            if (best != i) {
                int tmpC = conteggi[i];
                conteggi[i] = conteggi[best];
                conteggi[best] = tmpC;

                String tmpS = opzioni[i];
                opzioni[i] = opzioni[best];
                opzioni[best] = tmpS;
            }
        }

        int max = (opzioniCount > 0) ? conteggi[0] : 0;
        int min = (opzioniCount > 0) ? conteggi[opzioniCount - 1] : 0;

        String report = "";
        report = report + "SONDAGGIO: " + t + "\n";
        report = report + "Risposte totali: " + risposte.length + "\n";
        report = report + "Risposte valide: " + valide + "\n";
        report = report + "Risposte vuote/nulle: " + vuote + "\n";
        report = report + "Opzioni distinte: " + opzioniCount + "\n";
        report = report + "\n";

        if (valide == 0) {
            report = report + "Nessuna risposta valida.\n";
            return report;
        }

        report = report + "RISULTATI (dal più scelto):\n";
        for (int i = 0; i < opzioniCount; i++) {
            int c = conteggi[i];
            int perc = (int) ((c * 100.0) / valide + 0.5); // percentuale arrotondata
            report = report + "- " + opzioni[i] + ": " + c + " (" + perc + "%)\n";
        }

        report = report + "\n";
        report = report + "TOP 3:\n";
        for (int i = 0; i < 3; i++) {
            if (i < opzioniCount) {
                int c = conteggi[i];
                int perc = (int) ((c * 100.0) / valide + 0.5);
                report = report + (i + 1) + ") " + opzioni[i] + " - " + c + " (" + perc + "%)\n";
            } else {
                report = report + (i + 1) + ") (n/a)\n";
            }
        }

        report = report + "\n";
        report = report + "NOTE:\n";
        report = report + "- Più scelta: " + (opzioniCount > 0 ? opzioni[0] : "(n/a)") + " (" + max + ")\n";
        report = report + "- Meno scelta: " + (opzioniCount > 0 ? opzioni[opzioniCount - 1] : "(n/a)") + " (" + min + ")\n";

        report = report + "\n";
        report = report + "ISTOGRAMMA:\n";
        for (int i = 0; i < opzioniCount; i++) {
            String nome = opzioni[i];
            int c = conteggi[i];

            int target = 18;
            String nomeCol = nome;
            if (nomeCol.length() > target) {
                nomeCol = nomeCol.substring(0, target - 1) + "…";
            }
            while (nomeCol.length() < target) nomeCol = nomeCol + " ";

            String barre = "";
            int barreN = (int) ((c * 20.0) / valide + 0.5);
            for (int b = 0; b < barreN; b++) barre = barre + "#";

            report = report + nomeCol + " | " + barre + " (" + c + ")\n";
        }

        return report;
    }
}
