import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Wall implements Structure {

    //Założenia:
    //1. CompositeBlock jest zrobiony z danego materialu / ma dany kolor tylko,
    //jeśli wszystkie bloki z których się składa są stworzone z danego materiału / mają dany kolor
    //2.
    private static List<Block> blocks = new ArrayList<>();

    public static void main(String[] args) {
        blocks.add(new BlockImpl());
        blocks.add(new CompositeBlockImpl());
        blocks.add(new CompositeBlockImpl());
        blocks.add(new BlockImpl());
        blocks.add(new BlockImpl());
        for (Block b : blocks) {
            System.out.println(b instanceof CompositeBlock);
        }
        System.out.println("Hello world!");
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return Optional.empty();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> temp = new ArrayList<>();
        blocks.forEach(block -> {
            if (block instanceof CompositeBlock compositeBlock) {
                if (isCompositeBlockMadeOfRequiredMaterial(compositeBlock, material)) {
                    temp.add(block);
                }
            } else {
                if (block.getMaterial().equals(material)) {
                    temp.add(block);
                }
            }
        });
        return temp;
    }

    private boolean isCompositeBlockMadeOfRequiredMaterial(CompositeBlock compositeBlock, String material) {
        return compositeBlock.getBlocks()
                .stream()
                .allMatch(compositeBlockElement -> compositeBlockElement.getMaterial().equals(material));
    }

    private boolean isBlockMadeOfRequiredMaterial(Block block, String material) {
        return block.getMaterial().equals(material);
    }

    @Override
    public int count() {
        return blocks.size();
    }
}