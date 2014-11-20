/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mn.le.bataka.jbw.bundle.file.content;

import mn.le.bataka.jbw.bundle.file.content.assets.AssetLoaderFileContentBundle;
import mn.le.bataka.jbw.bundle.file.content.editor.EditorModule;
import mn.le.farcek.common.NamedContainer;
import mn.le.farcek.jbw.api.IModule;
import mn.le.farcek.jbw.api.bundle.JBWAbstractBundle;

/**
 *
 * @author work
 */
public class JBWBundleFileContent extends JBWAbstractBundle {

    @Override
    public void setupModules(NamedContainer<IModule> modules) {
        modules.add(new EditorModule(this));
    }
    
    @Override
    public String getName() {
        return "file.content";
    }

    @Override
    protected Class getAssetReaderClass() {
        return AssetLoaderFileContentBundle.class;
    }
    
    
}
