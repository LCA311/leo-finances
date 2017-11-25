package de.slg.leoappabi;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

/**
 * InformationDialog.
 * <p>
 * Ein allgemeiner simpler Dialog zum Anzeigen von wichtigen Infomationen. Vereinfachte Version der LeoApp-Klasse.
 *
 * @author Gianni
 * @version 2017.2411
 * @since 0.0.1
 */

@SuppressWarnings("unused")
final class InformationDialog extends AlertDialog {

    private String text;

    /**
     * Konstruktor. Instanziiert den Dialog ohne festgelegten Text. Vor dem Anzeigen muss {@link #setText(String)} aufgerufen werden!
     *
     * @param context Beliebige laufende Activity
     */
    InformationDialog(@NonNull Context context) {
        super(context);
    }

    /**
     * Konstruktor. Instanziiert den Dialog mit übergebenem Text.
     *
     * @param context Beliebige laufende Activity
     * @param text    Dialogtext als String
     */
    public InformationDialog(@NonNull Context context, String text) {
        super(context);
        this.text = text;
    }

    /**
     * Initialisiert TextView und "OK"-Button. Wird intern aufgerufen.
     *
     * @param b Bundle-Metadaten
     */
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.dialog_general);

        ((TextView) findViewById(R.id.textView10)).setText(text);
        findViewById(R.id.buttonOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    /**
     * Setzt den Dialogtext
     *
     * @param text Neuer Dialogtext
     * @return Aktuelles Dialogobjekt
     */
    public InformationDialog setText(String text) {
        this.text = text;
        return this;
    }
}