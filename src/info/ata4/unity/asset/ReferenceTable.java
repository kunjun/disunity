/*
 ** 2013 August 16
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package info.ata4.unity.asset;

import info.ata4.io.DataInputReader;
import info.ata4.io.DataOutputWriter;
import info.ata4.io.Struct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class ReferenceTable extends AssetStruct implements Iterable<Reference> {

    private final List<Reference> refs = new ArrayList<>();

    public List<Reference> getReferences() {
        return refs;
    }
    
    @Override
    public void read(DataInputReader in) throws IOException {
        int entries = in.readInt();
        for (int i = 0; i < entries; i++) {
            Reference ref = new Reference();
            ref.setAssetVersion(assetVersion);
            ref.read(in);
            refs.add(ref);
        }
    }

    @Override
    public void write(DataOutputWriter out) throws IOException {
        int entries = refs.size();
        out.writeInt(entries);

        for (Reference ref : refs) {
            ref.setAssetVersion(assetVersion);
            ref.write(out);
        }
    }

    @Override
    public Iterator<Reference> iterator() {
        return refs.iterator();
    }
}
