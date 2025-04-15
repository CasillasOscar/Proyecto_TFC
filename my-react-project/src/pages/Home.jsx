import ProductCard from "../components/ProductCard";

const mockProducts = [
  { id: 1, name: "Manzanas", price: 2.5, image: "https://via.placeholder.com/150" },
  { id: 2, name: "Leche", price: 1.2, image: "https://via.placeholder.com/150" },
  { id: 3, name: "Pan", price: 1.0, image: "https://via.placeholder.com/150" },
];

export default function Home() {
  return (
    <main className="p-6 grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
      {mockProducts.map((p) => (
        <ProductCard key={p.id} product={p} />
      ))}
    </main>
  );
}
