package oldmoon.dustw.tinkerdream.client.book;

import oldmoon.dustw.tinkerdream.tools.ModToolsList;
import slimeknights.mantle.client.book.data.BookData;
import slimeknights.mantle.client.book.data.PageData;
import slimeknights.mantle.client.book.data.SectionData;
import slimeknights.mantle.client.book.repository.BookRepository;
import slimeknights.tconstruct.library.book.content.ContentListing;
import slimeknights.tconstruct.library.book.content.ContentTool;
import slimeknights.tconstruct.library.book.sectiontransformer.SectionTransformer;
import slimeknights.tconstruct.library.tools.ToolCore;

/**
 * @author NmmOC7
 */
public class ModToolsTransformer extends SectionTransformer {
    private final BookRepository source;

    public ModToolsTransformer(BookRepository source) {
        super("tools");
        this.source = source;
    }

    @Override
    public void transform(BookData book, SectionData section) {
        ContentListing listing = (ContentListing) section.pages.get(0).content;
        for (ToolCore tool: ModToolsList.TOOLS_LIST) {
            PageData page = new PageData();
            page.source = source;
            page.parent = section;
            page.type = ContentTool.ID;
            page.data = "tools/" + tool.getIdentifier() + ".json";
            section.pages.add(page);
            page.load();
            listing.addEntry(tool.getLocalizedName(), page);
        }
    }
}
